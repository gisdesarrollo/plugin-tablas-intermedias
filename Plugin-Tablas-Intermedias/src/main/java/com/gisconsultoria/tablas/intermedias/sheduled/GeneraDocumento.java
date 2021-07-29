package com.gisconsultoria.tablas.intermedias.sheduled;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.model.Cabecero;
import com.gisconsultoria.tablas.intermedias.model.Detalle;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;
import com.gisconsultoria.tablas.intermedias.service.ICabeceroService;
import com.gisconsultoria.tablas.intermedias.service.IDetalleService;
import com.gisconsultoria.tablas.intermedias.services.IConnectXsa;

@Configuration
@EnableScheduling
public class GeneraDocumento {

	private static final Logger LOG = LoggerFactory.getLogger(GeneraDocumento.class);

	@Autowired
	private ICabeceroService cabeceroService;
	
	@Autowired 
	private IDetalleService detalleService;
	
	@Autowired
	private IConnectXsa connectXsa;

	@Scheduled(cron = "10 26 12 * * *", zone = "America/Mexico_City")
	public void generaCfdiXsa() {
		int doc = 31325;
		List<Cabecero> listCA;
		
		try {	
			LOG.info("Iniciando tablas intermedias");
			LOG.info("Obteniendo Datos..");
			Cabecero CAB = cabeceroService.findByDoc(doc);
			listCA = cabeceroService.findCAByStatus();
			System.out.println(listCA.size());
			if(listCA.size() > 0 || !listCA.isEmpty()) {
				for(Cabecero CA: listCA) {
					if(CA.getDOC()==doc) {
					connectXsa.connectApiXsaGeneraCFDI(CA.getDOC(),CA.getTAX());
					}
				}
			}else {
				LOG.info("No se encontraron datos con estatus A");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error al momento de la ejecución");
		}
	}
	

	
	

}
