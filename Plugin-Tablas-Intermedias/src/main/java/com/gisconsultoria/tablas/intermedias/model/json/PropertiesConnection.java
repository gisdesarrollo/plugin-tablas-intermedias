package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertiesConnection {
	
	@JsonProperty
	private String server;
	@JsonProperty
	private PropertiesConnectionTable TableName;
	@JsonProperty
	private String password;
	@JsonProperty
	private String port;
	@JsonProperty
	private String DBname;
	@JsonProperty
	private String user;
		
	public PropertiesConnection() {}
	
	public PropertiesConnection(String server, PropertiesConnectionTable TableName, String password, String port, String DBname, String user) {
		this.server = server;
		this.TableName = TableName;
		this.password = password;
		this.port = port;
		this.DBname = DBname;
		this.user = user;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDBname() {
		return DBname;
	}
	public void setDBname(String dBname) {
		DBname = dBname;
	}
	public PropertiesConnectionTable getTableName() {
		return TableName;
	}
	public void setTableName(PropertiesConnectionTable tableName) {
		TableName = tableName;
	}
	
	

}


