package com.gisconsultoria.tablas.intermedias.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.model.Detalle;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;
import com.gisconsultoria.tablas.intermedias.service.ICabeceroService;
import com.gisconsultoria.tablas.intermedias.service.IDetalleService;

@Service
public class ConnectXsa implements IConnectXsa {

	private static final Logger LOG = LoggerFactory.getLogger(ConnectXsa.class);

	// @Value("${xsa.id.cfd}")
	// private String idCfd;

	// @Value("${xsa.id.sucursal}")
	// private String idSucursal;

	// @Value("${url.servidor.xsa}")
	// private String urlServidorXsa;

	// @Value("${api.key.xsa}")
	// private String keyXsa;

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
	public void connectApiXsaGeneraCFDI(int doc, String tax) {

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
			List<PropertiesConf> dataList = getDataXsa();
			if (dataList.size() > 0) {
				for (PropertiesConf data : dataList) {
					// encode JSONObject params
					payload.put("idTipoCfd", data.getXSA().getIdTipoCfd());
					payload.put("idSucursal", data.getXSA().getIdSucursal());
					// get archivo fuente
					StringBuffer stringData = new StringBuffer();
					if (doc < 1) {
						throw new IOException("Error parametro Doc null");
					}
					LOG.info("Obteniendo parametros para generar el CFDI");
					List<Detalle> listDetalle = detalleService.finByDoc(doc);
					if (listDetalle.size() < 1 || listDetalle.isEmpty()) {
						throw new IOException("no se encontraron detalles");
					}

					for (Detalle DE : listDetalle) {
						stringData.append(DE.getDLFE());
						if (listDetalle.size() != count) {
							stringData.append("\n");
						}
						count++;
						nameFile = DE.getDCT() + "-" + DE.getDOC();
					}
					payload.put("nombre", nameFile);
					// get json data
					payload.put("archivoFuente", stringData);
					System.out.println(payload);
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
						cabeceroService.updatEstatusByDoc(error, doc);
						cabeceroService.updateDescByDoc(response, doc);
						LOG.error("Error code " + responseCode + " message: " + response);
						throw new IOException("Error code " + responseCode + " message: " + response);
					} else {
						System.out.println(jsonResponse);
						ResponseSuccessful result = decodeJsonSuccessful(jsonResponse.toString());

						cabeceroService.updatEstatusByDoc(successful, doc);
						response = result.getSerie() + "-" + result.getFolio() + " Generado correctamente";
						LOG.info(result.getSerie() + "-" + result.getFolio() + " Generado correctamente con UUID: "
								+ result.getUuid());
						cabeceroService.updateDescByDoc(response, doc);

						LOG.info("Iniciando descarga de archivos..");
						cfdiGenerados.connectApiXsaDowloadCfdi(xml, result, doc, tax, data);
						cfdiGenerados.connectApiXsaDowloadCfdi(pdf, result, doc, tax, data);
					}
				}
			}else {
				throw new IOException("Error no se encontraron datos del XSA conf");
			}

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

	public List<PropertiesConf> getDataXsa() {
		PropertiesConf data = null;
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		List<PropertiesConf> propertiConf = new ArrayList<>();
		try {
			FileReader reader = new FileReader("C:\\XML_PDF\\conf.json");
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
