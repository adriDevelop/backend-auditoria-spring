package com.adridevelop.spring_evasys.models.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CentroDAO;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.Dao.EmpleadoDAO;
import com.adridevelop.spring_evasys.models.entities.Centro;
import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service
public class DepartamentoCentroService{
	
	@Autowired
	private CentroDAO centroDao;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private DepartamentoDAO departamentoDao;

	public Departamento save(Long idCentro, Departamento departamento){
		Centro centro = centroDao.findById(idCentro).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el centro con id " + idCentro));
		centro.getDepartamentos().add(departamento);
		departamento.getCentros().add(centro);
		return departamento;
	}
	
	public List<Coordinador> getCoordinadorCentroDepartamento(Long idCentro, Long idDepartamento) {
		
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el departamento con id " + idDepartamento));
		Centro centro = departamento.getCentros().stream().filter(c -> c.getId_centro() == idCentro).findFirst().orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado un centro con id " + idCentro + " dentro del departamento indicado"));
		return centro.getCoordinadores();
	}
	
	public Centro getCentroByIdDepartamento(Long idDepartamento) {
		Centro centro = centroDao.findByDepartamentos(departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el departamento con id " + idDepartamento)));
		return centro;
	}
}
