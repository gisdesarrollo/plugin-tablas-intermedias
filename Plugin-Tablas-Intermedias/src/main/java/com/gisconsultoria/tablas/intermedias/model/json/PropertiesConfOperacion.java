package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConfOperacion {
	
		@JsonProperty
		private String Descarga;
		@JsonProperty
		private String PathDescarga;
		
		public PropertiesConfOperacion() {}
		public PropertiesConfOperacion(String Descarga, String PathDescarga) {
			this.Descarga = Descarga;
			this.PathDescarga = PathDescarga;
		}
		public String getDescarga() {
			return Descarga;
		}
		public void setDescarga(String descarga) {
			this.Descarga = descarga;
		}
		public String getPathDescarga() {
			return PathDescarga;
		}
		public void setPathDescarga(String pathDescarga) {
			this.PathDescarga = pathDescarga;
		}
		
}
