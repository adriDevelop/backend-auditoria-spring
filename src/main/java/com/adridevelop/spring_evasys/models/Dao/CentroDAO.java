package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Centro;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@Repository
public interface CentroDAO extends JpaRepository<Centro, Long>{

	List<Centro> findByActivo(Integer activo);
	Centro findByDepartamentos(Departamento departamento);
	Optional<List<Centro>> findByNombreContaining(String nombre);
}
