package com.adridevelop.spring_evasys.models.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.ColectivoDAO;
import com.adridevelop.spring_evasys.models.Dao.DepartamentoDAO;
import com.adridevelop.spring_evasys.models.entities.Colectivo;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Service
public class ColectivoDepartamentoService {
	
	@Autowired
	private ColectivoDAO colectivoDao;
	
	@Autowired
	private DepartamentoDAO departamentoDao;
	
	
	public Colectivo addDepartamentoColectivo(Colectivo colectivo, Long idDepartamento) {
		
		Departamento departamento = departamentoDao.findById(idDepartamento).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado un departamento con id " + idDepartamento));
		departamento.getColectivos().add(colectivo);
		colectivo.getDepartamentos().add(departamento);
		return colectivo;
	}

}
