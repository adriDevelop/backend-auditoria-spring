package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.ColectivoDAO;
import com.adridevelop.spring_evasys.models.entities.Colectivo;

@Service
public class ColectivoServiceImpl implements GeneralService<Colectivo>{
	
	@Autowired
	ColectivoDAO colectivoDao;

	@Override
	public List<Colectivo> findAll() {
		return colectivoDao.findAll();
	}

	@Override
	public Colectivo findById(Long id) {
		return colectivoDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrado el colectivo con id " + id));
	}

	@Override
	public List<Colectivo> findAllActivos() {
		return colectivoDao.findByActivo(1);
	}

	@Override
	public Colectivo save(Colectivo element) {
		return colectivoDao.save(element);
	}

}
