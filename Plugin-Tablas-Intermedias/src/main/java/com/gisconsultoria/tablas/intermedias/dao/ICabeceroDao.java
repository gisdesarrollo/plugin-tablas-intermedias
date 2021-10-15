package com.gisconsultoria.tablas.intermedias.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gisconsultoria.tablas.intermedias.model.Cabecero;
import com.gisconsultoria.tablas.intermedias.model.composite.CompositeKeyCA;

public interface ICabeceroDao extends CrudRepository<Cabecero, CompositeKeyCA> {
	
	@Query("select c from Cabecero c where c.FESTST='A' and c.FETAX=?1")
	public List<Cabecero> findCAByStatus(String tax);
	
	@Query("select c from Cabecero c where c.FEDOC = ?1 and c.FESTST='A'")
	public Cabecero findByDoc(int doc);
	
	@Modifying
	@Query("update Cabecero c set c.FESTST = ?1 where c.FEDOC = ?2 and FEDCT = ?3 and FETAX = ?4")
	public void updatEstatusByDoc(String estatus,int doc,String dct, String tax);
	
	@Modifying
	@Query("update Cabecero c set c.FEDESC = ?1 where c.FEDOC = ?2 and FEDCT = ?3 and FETAX = ?4")
	public void updateDescByDoc(String descripcion,int doc,String dct, String tax);

}
