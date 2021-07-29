package com.gisconsultoria.tablas.intermedias.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gisconsultoria.tablas.intermedias.model.Cabecero;
import com.gisconsultoria.tablas.intermedias.model.composite.CompositeKeyCA;

public interface ICabeceroDao extends CrudRepository<Cabecero, CompositeKeyCA> {
	
	@Query("select c from Cabecero c where c.STST='A'")
	public List<Cabecero> findCAByStatus();
	
	@Query("select c from Cabecero c where c.DOC = ?1 and c.STST='A'")
	public Cabecero findByDoc(int doc);
	
	@Modifying
	@Query("update Cabecero c set c.STST = ?1 where c.DOC = ?2")
	public void updatEstatusByDoc(String estatus,int doc);
	
	@Modifying
	@Query("update Cabecero c set c.DESC = ?1 where c.DOC = ?2")
	public void updateDescByDoc(String descripcion,int doc);

}
