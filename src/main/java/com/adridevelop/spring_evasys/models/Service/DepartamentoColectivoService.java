package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.ColectivoDAO;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.entities.Colectivo;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service 
public class DepartamentoColectivoService {
	
	@Autowired
	DepartamentoDAO departamentoDao;
	
	@Autowired
	ColectivoDAO colectivoDao;
	
	public Departamento actualizarDepartamento(List<Long>idColectivos, Departamento dept) {
		
		Departamento departamento = departamentoDao.findById(dept.getId_departamento()).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al departamento con id " + dept.getId_departamento()));
		departamento.setNombre(dept.getNombre());
		
		idColectivos.forEach(colectivoId -> {
			Colectivo colectivo = colectivoDao.findById(colectivoId).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el colectivo con id " + colectivoId));
			departamento.getColectivos().add(colectivo);
			colectivo.getDepartamentos().add(dept);
			
		});
		
		return departamento;
	}
	
public Departamento crearDepartamento(List<Long>idColectivos, Departamento dept) {
		
		idColectivos.forEach(colectivoId -> {
			Colectivo colectivo = colectivoDao.findById(colectivoId).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el colectivo con id " + colectivoId));
			dept.getColectivos().add(colectivo);
			colectivo.getDepartamentos().add(dept);
		});
		
		return dept;
	}

}
