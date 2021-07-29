package com.gisconsultoria.tablas.intermedias.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gisconsultoria.tablas.intermedias.model.Cabecero;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;
import com.gisconsultoria.tablas.intermedias.service.ICabeceroService;

@Service
public class GetCfdiGenerados implements IGetCfdiGenerados {

	private static final Logger LOG = LoggerFactory.getLogger(GetCfdiGenerados.class);

	//@Value("${url.servidor.xsa}")
	//private String urlServidorXsa;

	//@Value("${api.key.xsa}")
	//private String keyXsa;

	@Value("${url.api.xsa.descarga}")
	private String urlAPiDescarga;

	@Value("${port.servidor.xsa}")
	private String portXsa;

	@Value("${path.download.files}")
	private String pathArchivos;

	@Autowired
	private ICabeceroService cabeceroService;

	@Override
	public void connectApiXsaDowloadCfdi(String tipoDoc, ResponseSuccessful response, int doc,String tax,PropertiesConf data) {
		InputStream in = null;
		File zip = null;
		URL url = null;
		String filename = null;

		try {
			// get path
			String path = getPath(pathArchivos, doc,tax);
			if (tipoDoc == "XML") {
				url = new URL("https://"+data.getXSA().getServer().concat(":" + portXsa).concat(response.getXmlDownload()));
			} else {
				url = new URL("https://"+data.getXSA().getServer().concat(":" + portXsa).concat(response.getPdfDownload()));
			}
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			// get filename
			String raw = conn.getHeaderField("Content-Disposition");
			filename = getFileName(raw);

			if (filename == null) {
				filename = path
						.concat("\\"+response.getSerie() + "-" + response.getFolio().concat("." + tipoDoc.toLowerCase()));
			} else {
				filename = path.concat("\\"+filename);
			}
			LOG.info("Descargando archivo: " + filename);
			InputStream is = conn.getInputStream();
			in = new BufferedInputStream(is, 1024);

			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
			copyInputStream(in, out);
			out.close();
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			LOG.error("Error al momento de formar la URL");
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Error al momento de la ejecución");
		}

	}

	public void isExistPath(String path) throws IOException {
		File pathFile = new File(path);
		try {
			if (!pathFile.exists()) {
				if (!buildDirectory(pathFile)) {
					throw new IOException("No se puede crear el directorio: " + path);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[2048];

		int len = in.read(buffer);
		while (len >= 0) {
			out.write(buffer, 0, len);
			len = in.read(buffer);
		}

		in.close();
		out.close();
	}

	private boolean buildDirectory(File file) {
		return file.exists() || file.mkdir();
	}

	private String getFileName(String raw) {
		String fileName = null;
		if (raw != null && raw.indexOf("=") != -1) {
			fileName = raw.split("=")[1];
		}
		return fileName;
	}

	private String getPath(String path, int doc,String tax) {
		String pathCompleto = null;
		try {
			if(tax!=null) {
				pathCompleto = path.concat(tax);
				isExistPath(pathCompleto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathCompleto;
	}
}
