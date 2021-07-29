package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConnectionTable {

	@JsonProperty
	private String CA;
	@JsonProperty
	private String DE;
	@JsonProperty
	private String CN;
	
	
	public PropertiesConnectionTable() {}
	
	public PropertiesConnectionTable(String cA, String dE, String cN) {
		CA = cA;
		DE = dE;
		CN = cN;
	}
	public String getCA() {
		return CA;
	}
	public void setCA(String cA) {
		CA = cA;
	}
	public String getDE() {
		return DE;
	}
	public void setDE(String dE) {
		DE = dE;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	
	
}
