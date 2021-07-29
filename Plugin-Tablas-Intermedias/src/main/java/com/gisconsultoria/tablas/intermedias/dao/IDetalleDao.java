package com.gisconsultoria.tablas.intermedias.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gisconsultoria.tablas.intermedias.model.Detalle;
import com.gisconsultoria.tablas.intermedias.model.composite.CompositeKeyDE;

public interface IDetalleDao extends CrudRepository<Detalle, CompositeKeyDE> {

	@Query("select d from Detalle d where d.DOC= ?1 ")
	public List<Detalle> findByDoc(int doc);
}
