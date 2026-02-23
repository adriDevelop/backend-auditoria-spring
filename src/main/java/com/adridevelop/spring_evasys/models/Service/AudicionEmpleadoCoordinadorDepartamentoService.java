package com.adridevelop.spring_evasys.models.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.ColectivoDAO;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.Dao.EmpleadoDAO;
import com.adridevelop.spring_evasys.models.entities.Audicion;
import com.adridevelop.spring_evasys.models.entities.Colectivo;
import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Departamento;
import com.adridevelop.spring_evasys.models.entities.Empleado;

@Service
public class AudicionEmpleadoCoordinadorDepartamentoService {
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private CoordinadorDao coordinadorDao;
	
	@Autowired
	private DepartamentoDAO departamentoDao;
	
	@Autowired
	private ColectivoDAO colectivoDao;
	
	public Audicion asignarAudicionAEmpleadoCoordinadorDepartamento(Audicion audicion, Long idEmpleado, Long idCoordinadorEmpleado, Long idCoordinadorAuditel, Long idDepartamento, Long idColectivo) {
		
		Empleado empleado = empleadoDao.findById(idEmpleado).orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con id " + idEmpleado));
		Coordinador coordinadorEmpleado = coordinadorDao.findById(idCoordinadorEmpleado).orElseThrow(() -> new RecursoNoEncontradoException("Coordinador no encontrado con id " + idCoordinadorEmpleado));
		Coordinador coordinadorAuditel = coordinadorDao.findById(idCoordinadorAuditel).orElseThrow(() -> new RecursoNoEncontradoException("Coordinador no encontrado con id " + idCoordinadorAuditel));
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("Departamento no encontrado con id " + idDepartamento));
		Colectivo colectivo = colectivoDao.findById(idColectivo).orElseThrow(() -> new RecursoNoEncontradoException("Colectivo no encontrado con id " + idColectivo));
		
		empleado.getAuditorias().add(audicion);
		coordinadorEmpleado.getAuditorias().add(audicion);
		departamento.getAudiciones().add(audicion);
		colectivo.getAudiciones().add(audicion);
		
		audicion.setEmpleado(empleado);
		audicion.setCoordinador(coordinadorEmpleado);
		audicion.setCoordinador_auditel(coordinadorAuditel);
		audicion.setDepartamento(departamento);
		audicion.setColectivo(colectivo);
		
		return audicion;
		
	}
	
	

}
