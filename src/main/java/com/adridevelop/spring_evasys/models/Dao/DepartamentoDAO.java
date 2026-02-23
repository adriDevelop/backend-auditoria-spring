package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Departamento;

@Repository
public interface DepartamentoDAO extends JpaRepository<Departamento, Long>{
	
	List<Departamento> findByActivo(Integer activo);
	Departamento findByNombre(String nombre);
	Optional<List<Departamento>> findByNombreContaining(String nombre);

}
