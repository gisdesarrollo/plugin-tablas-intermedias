package com.gisconsultoria.tablas.intermedias.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.config.JpaConfig;
import com.gisconsultoria.tablas.intermedias.model.Detalle;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;
import com.gisconsultoria.tablas.intermedias.service.ICabeceroService;
import com.gisconsultoria.tablas.intermedias.service.IDetalleService;



@Service
public class ConnectXsa implements IConnectXsa {

	protected final Logger LOG = Logger.getLogger(ConnectXsa.class.getName());

	
	@Value("${url.api.xsa.generacion.cfdi}")
	private String urlAPiXsa;

	@Value("${port.servidor.xsa}")
	private String portXsa;

	@Autowired
	private IDetalleService detalleService;

	@Autowired
	private ICabeceroService cabeceroService;

	@Autowired
	private IGetCfdiGenerados cfdiGenerados;

	@Override
	public void connectApiXsaGeneraCFDI(int doc, String tax,String dct,PropertiesConf data) {

		HttpURLConnection urlConnection = null;
		StringBuffer jsonResponse = null;
		JSONObject payload = new JSONObject();
		String nameFile = null;
		String error = "03";
		String successful = "00";
		int count = 1;
		String response = null;
		String pdf = "PDF";
		String xml = "XML";
		try {
			// get Data XSA
			//List<PropertiesConf> dataList = getDataXsa();
			//if (dataList.size() > 0) {
			//	for (PropertiesConf data : dataList) {
					// encode JSONObject params
					if(dct.equals("TR")) {
						payload.put("idTipoCfd", data.getXSA().getIdTipoCfd2());
						payload.put("idSucursal", data.getXSA().getIdSucursal2());
					}else {
						payload.put("idTipoCfd", data.getXSA().getIdTipoCfd1());
						payload.put("idSucursal", data.getXSA().getIdSucursal1());
					}
					
					// get archivo fuente
					StringBuffer stringData = new StringBuffer();
					if (doc < 1) {
						LOG.error("Error parametro Doc null");
						throw new IOException("Error parametro Doc null");
					}
					LOG.info("Obteniendo parametros para generar el CFDI del RFC: "+data.getRFC());
					List<Detalle> listDetalle = detalleService.finByDoc(doc,dct,tax);
					if (listDetalle.size() < 1 || listDetalle.isEmpty()) {
						LOG.error("no se encontraron detalles");
						throw new IOException("no se encontraron detalles");
					}

					for (Detalle DE : listDetalle) {
						stringData.append(DE.getFEDLFE());
						if (listDetalle.size() != count) {
							stringData.append("\n");
						}
						count++;
						nameFile = DE.getFEDCT() + "-" + DE.getFEDOC();
					}
					payload.put("nombre", nameFile);
					// get json data
					payload.put("archivoFuente", stringData);
					//System.out.println(payload);
					byte[] payloadByte = payload.toString().getBytes("utf-8");
					// get conexion api
					URL urlApi = new URL("https://" + data.getXSA().getServer().concat(":" + portXsa)
							.concat("/" + data.getXSA().getKey()).concat(urlAPiXsa));
					URLConnection connection = urlApi.openConnection();
					urlConnection = (HttpURLConnection) connection;
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					bout.write(payloadByte);
					byte[] b = bout.toByteArray();

					urlConnection.setRequestProperty("Content-Length", String.valueOf(b.length));
					urlConnection.setRequestProperty("Accept", "application/json");
					urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
					urlConnection.setRequestMethod("PUT");
					urlConnection.setDoOutput(true);
					urlConnection.setDoInput(true);

					OutputStream out = urlConnection.getOutputStream();
					out.write(b);
					out.close();
					InputStreamReader isr = null;
					int responseCode = urlConnection.getResponseCode();
					if (urlConnection.getResponseCode() == 200) {
						isr = new InputStreamReader(urlConnection.getInputStream());
					} else {
						isr = new InputStreamReader(urlConnection.getErrorStream());
					}

					BufferedReader br = new BufferedReader(isr);
					jsonResponse = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						jsonResponse.append(line);
					}
					br.close();
					urlConnection.disconnect();
					if (responseCode != 200) {
						response = decodeJsonErrors(jsonResponse.toString());
						LOG.error("Error code " + responseCode + " message: " + response);
						cabeceroService.updatEstatusByDoc(error, doc,dct,tax);
						String respError="Error";
						cabeceroService.updateDescByDoc(respError, doc,dct,tax);
						throw new IOException("Error code " + responseCode + " message: " + response);
					} else {
						//LOG.info(jsonResponse);
						ResponseSuccessful result = decodeJsonSuccessful(jsonResponse.toString());

						cabeceroService.updatEstatusByDoc(successful, doc,dct,tax);
						response ="Generado correctamente";
						LOG.info(result.getSerie() + "-" + result.getFolio() + " Generado correctamente con UUID: "
								+ result.getUuid());
						cabeceroService.updateDescByDoc(response, doc,dct,tax);

						LOG.info("Iniciando descarga de archivos..");
						cfdiGenerados.connectApiXsaDowloadCfdi(xml, result, doc, tax, data);
						cfdiGenerados.connectApiXsaDowloadCfdi(pdf, result, doc, tax, data);
					}
				
				/*}*/
			/*}else {
				LOG.error("Error no se encontraron datos del XSA conf");
				throw new IOException("Error no se encontraron datos del XSA conf");
			}*/

		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Error al momento de la ejecución");
		}

	}

	public String decodeJsonErrors(String response) {
		JSONObject jsonObject = new JSONObject(response);
		JSONArray errors = jsonObject.getJSONArray("errors");
		String replace = errors.toString().replace("[", "");
		replace = replace.toString().replace("]", "");
		replace = replace.toString().replace("\"", "");
		return replace.toString();
	}

	public ResponseSuccessful decodeJsonSuccessful(String responseJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseSuccessful response = null;
		try {
			response = objectMapper.readValue(responseJson, ResponseSuccessful.class);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("error al momento de deserealizar Response");
		}
		return response;
	}
	
	@Override
	public List<PropertiesConf> getDataXsa() {
		PropertiesConf data = null;
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		List<PropertiesConf> propertiConf = new ArrayList<>();
		try {
			Path path = Paths.get("");
			String directoryJson = path.toAbsolutePath().toString();
			FileReader reader = new FileReader(directoryJson+File.separator+"conf.json");
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONObject jsonObj = new JSONObject(obj.toString());
			JSONArray jsonArr = (JSONArray) jsonObj.get("conf");
			for (int x = 0; x < jsonArr.length(); x++) {
				JSONObject jsonAO = jsonArr.getJSONObject(x);
				propertiConf.add(objectMapper.readValue(jsonAO.toString(), PropertiesConf.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error al momento de deserealizar el json: conf");
		}
		return propertiConf;
	}

}
