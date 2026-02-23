package com.adridevelop.spring_evasys.models.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.Dao.EmpleadoDAO;
import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Departamento;
import com.adridevelop.spring_evasys.models.entities.Empleado;

@Service
public class EmpleadoCoordinadorService {
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private CoordinadorDao coordinadorDao;
	
	@Autowired
	private DepartamentoDAO departamentoDao;
	
	public Empleado asignEmpleadoToCoordinadorAndDepartment(Long idCoordinador, Long idDepartamento, Empleado empleado) {
		
		Coordinador coordinador = coordinadorDao.findById(idCoordinador).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al coordinador con id " + idCoordinador));
		coordinador.getEmpleados().add(empleado);
		coordinador.getCentro().getEmpleados().add(empleado);
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el departamento con id " + idDepartamento));
		empleado.setDepartamento(departamento);
		empleado.setCentro(coordinador.getCentro());
		empleado.setCoordinador(coordinador);
		
		return empleado;
	}
	
	public List<Empleado> getEmpleadosCoordinadorNombre(Long idCoordinador, String nombreEmpleado){
		
		Coordinador coordinador = coordinadorDao.findById(idCoordinador).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al coordinador con id " + idCoordinador));
		return coordinador.getEmpleados().stream()
				.filter(empleado -> 
					empleado.getNombre().contains(nombreEmpleado)
				).toList();
	}
}
