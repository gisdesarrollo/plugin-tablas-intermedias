package com.gisconsultoria.tablas.intermedias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gisconsultoria.tablas.intermedias.dao.IDetalleDao;
import com.gisconsultoria.tablas.intermedias.model.Detalle;

@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private IDetalleDao detalleDao;
	

	@Override
	public List<Detalle> finByDoc(int doc) {
		return detalleDao.findByDoc(doc);
	}
}
