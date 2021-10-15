package com.gisconsultoria.tablas.intermedias.services;

import java.util.List;

import com.gisconsultoria.tablas.intermedias.model.json.PropertiesConf;

public interface IConnectXsa {

	public void connectApiXsaGeneraCFDI(int doc,String tax,String dct,PropertiesConf dataList);

	public List<PropertiesConf> getDataXsa();


}
