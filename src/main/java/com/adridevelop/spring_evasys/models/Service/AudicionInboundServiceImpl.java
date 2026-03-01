package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.AudicionDAO;
import com.adridevelop.spring_evasys.models.Dao.AudicionInboundDAO;
import com.adridevelop.spring_evasys.models.entities.Audicion;
import com.adridevelop.spring_evasys.models.entities.AudicionInbound;

@Service
public class AudicionInboundServiceImpl implements GeneralService<AudicionInbound>{
	
	
	@Autowired
	private AudicionInboundDAO audicionInboundDao;
	
	@Autowired
	private AudicionDAO audicionDao;

	@Override
	public List<AudicionInbound> findAll() {
		return this.audicionInboundDao.findAll();
	}

	@Override
	public AudicionInbound findById(Long id) {
		return this.audicionInboundDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado la audicion con la id " + id));
	}

	@Override
	public List<AudicionInbound> findAllActivos() {
		return null;
	}

	@Override
	public AudicionInbound save(AudicionInbound element) {
		return this.audicionInboundDao.save(element);
	}
	
	public AudicionInbound agregarInboundToAuditel(Long id, AudicionInbound element) {
		Audicion audicion = this.audicionDao.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado la audicion con la id " + id));
		AudicionInbound audicionInbound = this.save(element);
		audicion.setDetallesInbound(audicionInbound);
		audicionInbound.setAudicion(audicion);
		return audicionInbound;
	}

}
