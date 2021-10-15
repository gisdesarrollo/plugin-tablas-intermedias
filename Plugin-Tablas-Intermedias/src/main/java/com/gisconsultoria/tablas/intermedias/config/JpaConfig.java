package com.gisconsultoria.tablas.intermedias.config;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConnection;
import com.gisconsultoria.tablas.intermedias.sheduled.GeneraDocumento;





@Configuration
public class JpaConfig {
	
	  protected final Logger LOG = Logger.getLogger(JpaConfig.class.getName());

	@Bean
	public DataSource getDataSource()
	{
		//DataXsa xData= getDataXsa();
		PropertiesConnection data = getDataConnecction();
		DataSourceBuilder dataSourceBuilder=null;
		try {
			dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        dataSourceBuilder.url("jdbc:sqlserver://"+data.getServer()+":"+data.getPort()+";DatabaseName="+data.getDBname());
	        dataSourceBuilder.username(data.getUser());
	        dataSourceBuilder.password(data.getPassword());
	        return dataSourceBuilder.build();
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error en la conexion a la BD");
			System.out.println("Error de conexion");
			
		}
		return null;
		
	}
	
	
	public PropertiesConnection  getDataConnecction() {
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		PropertiesConnection data = null;
		try {
			//get path JSON
			Path path = Paths.get("");
			String directoryJson = path.toAbsolutePath().toString();
			
			FileReader reader = new FileReader(directoryJson+ File.separator +"conf.json");
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObj = new JSONObject(obj.toString());
            JSONObject DBC = (JSONObject) jsonObj.get("DBconnection");
            data = objectMapper.readValue(DBC.toString(), PropertiesConnection.class);
           
		} catch (FileNotFoundException e) {
			LOG.error("Error la ruta no existe");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	

}
