package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Coordinador;

@Repository
public interface CoordinadorDao extends JpaRepository<Coordinador, Long> {

	List<Coordinador> findByActivo(Integer activo);
	Optional<Coordinador> findOneByUsuario(String usuario);
	Optional<List<Coordinador>> findByNombreContainingOrUsuarioContaining(String nombre, String usuario);
	
} 