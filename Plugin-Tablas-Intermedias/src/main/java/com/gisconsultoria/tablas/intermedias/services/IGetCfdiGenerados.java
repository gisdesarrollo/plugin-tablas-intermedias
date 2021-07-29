package com.gisconsultoria.tablas.intermedias.services;

import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;
import com.gisconsultoria.tablas.intermedias.model.json.ResponseSuccessful;

public interface IGetCfdiGenerados {

	public void connectApiXsaDowloadCfdi(String tipoDoc,ResponseSuccessful response,int doc,String tax, PropertiesConf data);

}
