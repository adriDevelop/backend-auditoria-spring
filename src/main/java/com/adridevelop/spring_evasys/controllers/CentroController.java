package com.adridevelop.spring_evasys.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adridevelop.spring_evasys.models.Service.CentroServiceImpl;
import com.adridevelop.spring_evasys.models.dto.CentroDTO;
import com.adridevelop.spring_evasys.models.dto.CentroDepartamentoDTO;
import com.adridevelop.spring_evasys.models.entities.Centro;

@RestController
@RequestMapping("/api")
public class CentroController {
	
	@Autowired
	CentroServiceImpl servicio;
	
	@PostMapping("/centro")
	public ResponseEntity<Centro> save(@RequestBody CentroDTO centroDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(centroDto));
	}
	
	@GetMapping("/centros")
	public ResponseEntity<List<Centro>> findAllCentros(){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
	}
	
	@GetMapping("/centro/{id}")
	public ResponseEntity<Centro> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
	}
	
	@GetMapping("/centro-nombre/{nombre}")
	public ResponseEntity<List<Centro>> findByNombreContaining(@PathVariable String nombre){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findByNombreContaining(nombre));
	}
	
	@GetMapping("/centros-activos")
	public ResponseEntity<List<Centro>> findAllActivos(){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.findAllActivos());
	}
	
	@PutMapping("/centro")
	public ResponseEntity<Centro> update(@RequestBody Centro centro){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.save(centro));
	}
	
	@PutMapping("/addDepartamentoToCentro")
	public ResponseEntity<Centro> addDepartamentoToCentro(@RequestBody CentroDepartamentoDTO centroDto){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.addDepartamentoToCentro(centroDto));
	}

}
