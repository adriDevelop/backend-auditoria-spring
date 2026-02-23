package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.entities.Coordinador;

@Service
public class CoordinadorServiceImpl implements GeneralService<Coordinador>{
	
	// Inyectamos el dao para que se pueda llamar a los métodos que permiten realizar cambios en la bbdd.
	@Autowired
	CoordinadorDao coordinadorDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	// Método para actualizar o crear un Coordinador
	@Override
	public Coordinador save(Coordinador coordinador) {
		if (!coordinador.getPassword().startsWith("$2a")) {
			coordinador.setPassword(passwordEncoder.encode(coordinador.getPassword()));
		}
		return coordinadorDao.save(coordinador);
	}

	// Método que listará todos los Coordinadores que existan en la BBDD
	@Override
	public List<Coordinador> findAll() {
		return coordinadorDao.findAll();
	}
	
	public List<Coordinador> findByNombreOrUsuario(String nombre){
		return coordinadorDao.findByNombreContainingOrUsuarioContaining(nombre, nombre).orElseThrow(() -> new RecursoNoEncontradoException("No se ha podido encontrar al coordinador con nombre o usuario " + nombre));
	}

	// Método que buscará al coordinador con id y si no lo encuentra retornará una excepciḉon de tipo RuntimeException
	@Override
	public Coordinador findById(Long id) {
		return coordinadorDao.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha podido encontrar al coordinador con id " + id));
	}

	@Override
	public List<Coordinador> findAllActivos() {
		return coordinadorDao.findByActivo(1);
	}

}
