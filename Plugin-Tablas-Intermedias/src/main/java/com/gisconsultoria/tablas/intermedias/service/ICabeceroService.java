package com.gisconsultoria.tablas.intermedias.service;

import java.util.List;

import com.gisconsultoria.tablas.intermedias.model.Cabecero;

public interface ICabeceroService {

	public Cabecero findByDoc(int doc);

	public List<Cabecero> findCAByStatus();
	
	public void save(Cabecero cabecero);

	public void updatEstatusByDoc(String estatus,int doc);
	
	public void updateDescByDoc(String descripcion,int doc);
}
