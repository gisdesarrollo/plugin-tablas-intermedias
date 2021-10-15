package com.gisconsultoria.tablas.intermedias.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConfOperacion {
	
		@JsonProperty
		private String Descarga;
		@JsonProperty
		private String PathDescargaXML;
		@JsonProperty
		private String PathDescargaPDF;
		
		public PropertiesConfOperacion() {}

		public PropertiesConfOperacion(String descarga, String pathDescargaXML, String pathDescargaPDF) {
			Descarga = descarga;
			PathDescargaXML = pathDescargaXML;
			PathDescargaPDF = pathDescargaPDF;
		}

		public String getDescarga() {
			return Descarga;
		}

		public void setDescarga(String descarga) {
			Descarga = descarga;
		}

		public String getPathDescargaXML() {
			return PathDescargaXML;
		}

		public void setPathDescargaXML(String pathDescargaXML) {
			PathDescargaXML = pathDescargaXML;
		}

		public String getPathDescargaPDF() {
			return PathDescargaPDF;
		}

		public void setPathDescargaPDF(String pathDescargaPDF) {
			PathDescargaPDF = pathDescargaPDF;
		}
		
}
