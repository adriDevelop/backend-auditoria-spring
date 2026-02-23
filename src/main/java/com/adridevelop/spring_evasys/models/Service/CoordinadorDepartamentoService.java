package com.adridevelop.spring_evasys.models.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CentroDAO;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.entities.Centro;
import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service
public class CoordinadorDepartamentoService {
	
	@Autowired
	private CoordinadorDao coordinadorDao;
	
	@Autowired
	private CentroDAO centroDao;
	
	@Autowired
	private DepartamentoDAO departamentoDao;
	
	public Coordinador addCoordinadorToCentro(Long idCentro, Long idDepartamento, Coordinador coordinador) {
		
		Coordinador coord = coordinadorDao.findById(coordinador.getId_coordinador()).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado a ese coordinador"));
		
		coord.setNombre(coordinador.getNombre());
		coord.setApellidos(coordinador.getApellidos());
		coord.setUsuario(coordinador.getUsuario());
		
		
		Centro centro = centroDao.findById(idCentro).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el centro con id " + idCentro));
		centro.getCoordinadores().add(coordinador);
		coord.setCentro(centro);
		
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado departamento con ese id " + idDepartamento));
		departamento.getCoordinadores().add(coordinador);
		coord.setDepartamento(departamento);
		
		return coord;
	}
	
	public Coordinador addNewCoordinador(Long idCentro, Long idDepartamento, Coordinador coordinador) {
		Centro centro = centroDao.findById(idCentro).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el centro con id " + idCentro));
		centro.getCoordinadores().add(coordinador);
		coordinador.setCentro(centro);
		
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado departamento con ese id " + idDepartamento));
		departamento.getCoordinadores().add(coordinador);
		coordinador.setDepartamento(departamento);
		
		return coordinador;
	}
	
public Coordinador addCoordinadorToCentro(Long idCoordinador, Long idCentro, Long idDepartamento) {
	
		Coordinador coordinador = coordinadorDao.findById(idCoordinador).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el coordinador con id " + idCoordinador));
		
		Centro centro = centroDao.findById(idCentro).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el centro con id " + idCentro));
		centro.getCoordinadores().add(coordinador);
		coordinador.setCentro(centro);
		
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado departamento con ese id " + idDepartamento));
		departamento.getCoordinadores().add(coordinador);
		coordinador.setDepartamento(departamento);
		
		return coordinador;
	}

} 
