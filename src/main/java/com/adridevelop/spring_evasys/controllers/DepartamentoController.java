package com.adridevelop.spring_evasys.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adridevelop.spring_evasys.models.Service.CentroServiceImpl;
import com.adridevelop.spring_evasys.models.Service.DepartamentoCentroService;
import com.adridevelop.spring_evasys.models.Service.DepartamentoColectivoService;
import com.adridevelop.spring_evasys.models.Service.DepartamentoServiceImpl;
import com.adridevelop.spring_evasys.models.dto.DepartamentoCentroDTO;
import com.adridevelop.spring_evasys.models.dto.DepartamentoDTO;
import com.adridevelop.spring_evasys.models.entities.Centro;
import com.adridevelop.spring_evasys.models.entities.Departamento;

@RestController
@RequestMapping("/api")
public class DepartamentoController {

	// Inyectamos el service
	@Autowired
	DepartamentoServiceImpl departamentoService;
	@Autowired
	CentroServiceImpl centroService;
	@Autowired
	DepartamentoCentroService departamentoCentroService;
	@Autowired
	DepartamentoColectivoService departamentoColectivoService;
	
	// Método que devuelve un departamento por su id
	@GetMapping("/departamento/{id}")
	public ResponseEntity<Departamento> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findById(id));
	}
	
	// Método que devuelve un listado de departamentos
	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
	}
	
	@GetMapping("/departamentos-activos")
	public ResponseEntity<List<Departamento>> findAllActivos(){
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAllActivos());
	}
	
	@GetMapping("/departamento/centro/{id}")
	public ResponseEntity<?> findCentroByIdDepartamento(@PathVariable Long id){
		return ResponseEntity.ok(departamentoCentroService.getCentroByIdDepartamento(id));
	}
	
	@GetMapping("/departamento-nombre/{nombre}")
	public ResponseEntity<?> findCentroByNombre(@PathVariable String nombre){
		return ResponseEntity.ok(departamentoService.findByNombreContaining(nombre));
	}
	
	// Método que crea un departamento y lo almacena en la base de datos
	@PostMapping("/departamento")
	public ResponseEntity<Departamento> save(@RequestBody DepartamentoDTO departamentoDto){
		Departamento departamentoAgregar = departamentoColectivoService.crearDepartamento(departamentoDto.getIdColectivos(), departamentoDto.getDepartamento());
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.save(departamentoAgregar));
	}
	
	// Método que actualiza un departamento y lo almacena en la base de datos
	@PutMapping("/departamento")
	public ResponseEntity<Departamento> update(@RequestBody DepartamentoDTO departamentoDto){
		Departamento departamento = departamentoColectivoService.actualizarDepartamento(departamentoDto.getIdColectivos(), departamentoDto.getDepartamento());
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.save(departamento));
	}
	
}
