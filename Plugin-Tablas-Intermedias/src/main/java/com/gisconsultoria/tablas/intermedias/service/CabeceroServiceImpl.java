package com.gisconsultoria.tablas.intermedias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisconsultoria.tablas.intermedias.dao.ICabeceroDao;
import com.gisconsultoria.tablas.intermedias.model.Cabecero;

@Service
public class CabeceroServiceImpl implements ICabeceroService {
	
	@Autowired
	private ICabeceroDao cabeceroDao;
	
	
	@Override
	public Cabecero findByDoc(int doc) {
		return cabeceroDao.findByDoc(doc);
	}
	
	@Override
	public List<Cabecero> findCAByStatus(String tax){
		return cabeceroDao.findCAByStatus(tax);
	}

	@Override
	public void save(Cabecero cabecero) {
		cabeceroDao.save(cabecero);
		
	}

	@Override
	@Transactional
	public void updatEstatusByDoc(String estatus, int doc,String dct,String tax) {
		cabeceroDao.updatEstatusByDoc(estatus, doc, dct, tax);
		
	}

	@Override
	@Transactional
	public void updateDescByDoc(String descripcion, int doc,String dct,String tax) {
		cabeceroDao.updateDescByDoc(descripcion, doc,dct,tax);
	}
	

}
