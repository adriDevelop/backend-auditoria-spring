package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Colectivo;

@Repository
public interface ColectivoDAO extends JpaRepository<Colectivo, Long> {
	List<Colectivo> findByActivo(Integer activo);
}
