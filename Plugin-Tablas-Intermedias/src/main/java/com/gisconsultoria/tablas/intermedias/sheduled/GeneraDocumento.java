package com.gisconsultoria.tablas.intermedias.sheduled;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.model.Cabecero;
import com.gisconsultoria.tablas.intermedias.model.Detalle;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;
import com.gisconsultoria.tablas.intermedias.service.ICabeceroService;
import com.gisconsultoria.tablas.intermedias.service.IDetalleService;
import com.gisconsultoria.tablas.intermedias.services.IConnectXsa;

@Configuration
@EnableScheduling
public class GeneraDocumento {

	  protected final Logger LOG = Logger.getLogger(GeneraDocumento.class.getName());

	@Autowired
	private ICabeceroService cabeceroService;
	
	@Autowired 
	private IDetalleService detalleService;
	
	@Autowired
	private IConnectXsa connectXsa;

	//@Scheduled(cron = "* 0/5 * * * *", zone = "America/Mexico_City")
	@Scheduled(fixedDelay = 30000)
	public void generaCfdiXsa() {
		
		List<Cabecero> listCA;
		
		try {	
			LOG.info("Iniciando tablas intermedias");	
			// get Data XSA
			List<PropertiesConf> dataList = connectXsa.getDataXsa();
			if (dataList.size() > 0) {
				for (PropertiesConf data : dataList) {
					LOG.info("Obteniendo Datos de la sucursal: "+data.getRFC());
					listCA = cabeceroService.findCAByStatus(data.getRFC());
					if(listCA.size() > 0 || !listCA.isEmpty()) {
						for(Cabecero CA: listCA) {
							connectXsa.connectApiXsaGeneraCFDI(CA.getFEDOC(),CA.getFETAX(),CA.getFEDCT(),data);
						}
					}else {
						LOG.info("No se encontraron datos con estatus A");
					}
				}
			}
			
			LOG.info("Finalizando tablas intermedias..");
		
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error al momento de la ejecución");
		}
	}
	

	
	

}
