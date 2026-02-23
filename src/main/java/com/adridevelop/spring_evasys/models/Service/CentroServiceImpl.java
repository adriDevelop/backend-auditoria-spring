package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CentroDAO;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.dto.CentroDTO;
import com.adridevelop.spring_evasys.models.dto.CentroDepartamentoDTO;
import com.adridevelop.spring_evasys.models.entities.Centro;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service
public class CentroServiceImpl implements GeneralService<Centro> {

	@Autowired
	CentroDAO centroDao;
	
	@Autowired
	DepartamentoDAO departamentoDao;

	@Override
	public List<Centro> findAll() {
		return centroDao.findAll();
	}
	
	public List<Centro> findByNombreContaining(String nombre){
		return centroDao.findByNombreContaining(nombre).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar el centro con nombre " + nombre));
	}
	
	public Centro addDepartamentoToCentro(CentroDepartamentoDTO centroDepartamento) {
				
		Centro centro = centroDao.findById(centroDepartamento.getIdCentro()).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el centro con id " + centroDepartamento.getIdCentro()));
		
		centro.setNombre(centroDepartamento.getNombre());
		centro.getDepartamentos().forEach(dep -> {
			dep.getCentros().remove(centro);
		});
		centro.getDepartamentos().clear();
		
		centroDepartamento.getIdsDepartamentos().forEach(id -> {
			Departamento departamento = departamentoDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado departamento con id " + id));
			departamento.getCentros().add(centro);
			centro.getDepartamentos().add(departamento);
		});
		
		return centroDao.save(centro);
	}

	@Override
	public Centro findById(Long id) {
		return centroDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar al centro con id " + id));
	}

	@Override
	public List<Centro> findAllActivos() {
		return centroDao.findByActivo(1);
	}

	@Override
	public Centro save(Centro element) {
		return centroDao.save(element);
	}
	
	public Centro save(CentroDTO centroDto) {
		Centro centro = new Centro();
		centro.setNombre(centroDto.getNombre());
		centro.setLocalizacion(centroDto.getLocalizacion());
		centroDto.getIdsDepartamentos().forEach(dept -> {
			Departamento departamento = departamentoDao.findById(dept).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado el departamento con id " + dept));
			departamento.getCentros().add(centro);
			centro.getDepartamentos().add(departamento);
		});
		return centroDao.save(centro);
	}

}
