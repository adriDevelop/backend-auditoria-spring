package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.Dao.EmpleadoDAO;
import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Empleado;

@Service
public class EmpleadoServiceImpl implements GeneralService<Empleado>{
	
	@Autowired
	EmpleadoDAO empleadoDao;
	
	@Autowired
	CoordinadorDao coordinadorDao;

	@Override
	public List<Empleado> findAll() {
		return empleadoDao.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar el empleado con id " + id));
	}
	
	public List<Empleado> findByNombre(String nombre){
		return empleadoDao.findByNombreContaining(nombre).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar el empleado con nombre " + nombre));
	}
	
	public List<Empleado> findEmpleadosByCoordinador(Long id){
		Coordinador coordinador = this.coordinadorDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al coordinador con id " + id));
		return empleadoDao.findEmpleadosByCoordinador(coordinador).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al coordinador con id " + id));
	}

	@Override
	public List<Empleado> findAllActivos() {
		return empleadoDao.findByActivo(1);
	}

	@Override
	public Empleado save(Empleado element) {
		return empleadoDao.save(element);
	}
	
	public void deleteById(Long id) {
		empleadoDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al empleado con id " + id));
		empleadoDao.deleteById(id);
	}

}
