package com.gisconsultoria.tablas.intermedias.service;

import java.util.List;

import com.gisconsultoria.tablas.intermedias.model.Cabecero;

public interface ICabeceroService {

	public Cabecero findByDoc(int doc);

	public List<Cabecero> findCAByStatus(String tax);
	
	public void save(Cabecero cabecero);

	public void updatEstatusByDoc(String estatus,int doc,String dct,String tax);
	
	public void updateDescByDoc(String descripcion,int doc,String dct,String tax);
}
