package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConfXsa {
	
		@JsonProperty
		private String server;
		@JsonProperty
		private String key;
		@JsonProperty
		private String idTipoCfd1;
		@JsonProperty
		private String idTipoCfd2;
		@JsonProperty
		private String idSucursal1;
		@JsonProperty
		private String idSucursal2;
		
		
		
		public PropertiesConfXsa() {}
		
		public PropertiesConfXsa(String server, String key, String idTipoCfd1, String idTipoCfd2, String idSucursal1,
				String idSucursal2) {
			this.server = server;
			this.key = key;
			this.idTipoCfd1 = idTipoCfd1;
			this.idTipoCfd2 = idTipoCfd2;
			this.idSucursal1 = idSucursal1;
			this.idSucursal2 = idSucursal2;
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

		public String getIdTipoCfd1() {
			return idTipoCfd1;
		}

		public void setIdTipoCfd1(String idTipoCfd1) {
			this.idTipoCfd1 = idTipoCfd1;
		}

		public String getIdTipoCfd2() {
			return idTipoCfd2;
		}

		public void setIdTipoCfd2(String idTipoCfd2) {
			this.idTipoCfd2 = idTipoCfd2;
		}

		public String getIdSucursal1() {
			return idSucursal1;
		}

		public void setIdSucursal1(String idSucursal1) {
			this.idSucursal1 = idSucursal1;
		}

		public String getIdSucursal2() {
			return idSucursal2;
		}

		public void setIdSucursal2(String idSucursal2) {
			this.idSucursal2 = idSucursal2;
		}
		
	
}
