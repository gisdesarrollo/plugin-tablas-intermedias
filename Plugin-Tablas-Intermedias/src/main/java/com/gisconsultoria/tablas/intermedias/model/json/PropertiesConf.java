package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PropertiesConf {
	
	@JsonProperty
	private String RFC;
	@JsonProperty
	private PropertiesConfXsa XSA;
	@JsonProperty
	private PropertiesConfOperacion Operacion;
	
	public PropertiesConf() {}
	
	public PropertiesConf(String rFC, PropertiesConfXsa XSA, PropertiesConfOperacion Operacion) {
		this.RFC = rFC;
		this.XSA = XSA;
		this.Operacion = Operacion;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		this.RFC = rFC;
	}
	public PropertiesConfXsa getXSA() {
		return XSA;
	}
	public void setXSA(PropertiesConfXsa xSA) {
		this.XSA = xSA;
	}
	public PropertiesConfOperacion getOperacion() {
		return Operacion;
	}
	public void setOperacion(PropertiesConfOperacion operacion) {
		Operacion = operacion;
	}
	
}

