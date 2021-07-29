package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConfXsa {
	
		@JsonProperty
		private String server;
		@JsonProperty
		private String key;
		@JsonProperty
		private String idTipoCfd;
		@JsonProperty
		private String idSucursal;
		
		
		
		public PropertiesConfXsa() {}
		public PropertiesConfXsa(String server, String key, String idTipoCfd, String idSucursal) {
			
			this.server = server;
			this.key = key;
			this.idTipoCfd = idTipoCfd;
			this.idSucursal = idSucursal;
		}
		public String getServer() {
			return server;
		}
		public void setServer(String server) {
			this.server = server;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getIdTipoCfd() {
			return idTipoCfd;
		}
		public void setIdTipoCfd(String idTipoCfd) {
			this.idTipoCfd = idTipoCfd;
		}
		public String getIdSucursal() {
			return idSucursal;
		}
		public void setIdSucursal(String idSucursal) {
			this.idSucursal = idSucursal;
		}
		
		
	
}
