package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.AudicionDAO;
import com.adridevelop.spring_evasys.models.entities.Audicion;

@Service
public class AudicionServiceImpl implements GeneralService<Audicion>{
	
	@Autowired
	AudicionDAO audicionDao;

	@Override
	public List<Audicion> findAll() {
		return audicionDao.findAll();
	}

	@Override
	public Audicion findById(Long id) {
		return audicionDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No es posible localizar la audicion con id " + id));
	}

	@Override
	public List<Audicion> findAllActivos() {
		return audicionDao.findByActivo(1);
	}

	@Override
	public Audicion save(Audicion element) {
		return audicionDao.save(element);
	}

}
