package com.gisconsultoria.tablas.intermedias.service;

import java.util.List;

import com.gisconsultoria.tablas.intermedias.model.Detalle;

public interface IDetalleService {

	public List<Detalle> finByDoc(int id ,String dct,String tax);

}
