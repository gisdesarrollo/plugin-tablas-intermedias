package com.gisconsultoria.tablas.intermedias.config;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sql.DataSource;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConnection;





@Configuration
public class JpaConfig {
	
	@Bean
	public DataSource getDataSource()
	{
		//DataXsa xData= getDataXsa();
		PropertiesConnection data = getDataConnecction();
		DataSourceBuilder dataSourceBuilder=null;
		try {
			dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        dataSourceBuilder.url("jdbc:sqlserver://"+data.getServer()+":"+data.getPort()+";databaseName="+data.getDBname()+"");
	        dataSourceBuilder.username(data.getUser());
	        dataSourceBuilder.password(data.getPassword());
	        return dataSourceBuilder.build();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error de conexion");
			
		}
		return null;
		
	}
	
	
	public PropertiesConnection  getDataConnecction() {
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		PropertiesConnection data = null;
		try {
			FileReader reader = new FileReader("C:\\XML_PDF\\conf.json");
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObj = new JSONObject(obj.toString());
            JSONObject DBC = (JSONObject) jsonObj.get("DBconnection");
            data = objectMapper.readValue(DBC.toString(), PropertiesConnection.class);
           
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	

}
