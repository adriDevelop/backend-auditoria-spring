package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Coordinador;
import com.adridevelop.spring_evasys.models.entities.Empleado;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Long>{

	List<Empleado> findByActivo(Integer activo);
	Optional <List<Empleado>> findEmpleadosByCoordinador(Coordinador coordinador);
	Optional <List<Empleado>> findByNombreContaining(String nombre);
}
