package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service
public class DepartamentoServiceImpl implements GeneralService<Departamento>{

	// Inyectar el DAO para poder hacer las operaciones con la base de datos
	@Autowired
	DepartamentoDAO departamentoDao;
	
	@Override
	public Departamento findById(Long id) {
		return departamentoDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar el departamento con id " + id));
	}

	@Override
	public List<Departamento> findAll() {
		return departamentoDao.findAll();
	}
	
	public Departamento findByNombre(String nombre) {
		return departamentoDao.findByNombre(nombre);
	}
	
	public List<Departamento> findByNombreContaining(String nombre){
		return departamentoDao.findByNombreContaining(nombre).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar el departamento con nombre " + nombre));
	}

	@Override
	public Departamento save(Departamento departamento) {
		return departamentoDao.save(departamento);
	}

	@Override
	public List<Departamento> findAllActivos() {
		return departamentoDao.findByActivo(1);
	}

}
