package com.adridevelop.spring_evasys.models.Service;

import java.util.List;

import com.adridevelop.spring_evasys.models.dto.CentroDTO;
import com.adridevelop.spring_evasys.models.entities.Centro;

public interface GeneralService<T> {
	
	// Devuelve todos los elementos de la lista genérica
	List<T> findAll();
	
	// Devuelve un elemento de la lista genérica filtrado por id
	T findById(Long id);
	
	// Devuelve solo los elementos activos
	List<T> findAllActivos();
	
	// Devuelve el elemento actualizado o ingresado en la BBDD
	T save(T element);

}
