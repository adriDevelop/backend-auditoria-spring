package com.adridevelop.spring_evasys.controllers;

import java.util.List;

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

import com.adridevelop.spring_evasys.models.Service.ColectivoDepartamentoService;
import com.adridevelop.spring_evasys.models.Service.ColectivoServiceImpl;
import com.adridevelop.spring_evasys.models.dto.ColectivoDepartamentoDTO;
import com.adridevelop.spring_evasys.models.entities.Colectivo;

@RestController
@RequestMapping("/api")
public class ColectivoController {
	
	@Autowired
	ColectivoServiceImpl colectivoService;
	
	@Autowired
	ColectivoDepartamentoService colectivoDepartamentoService;
	
	@GetMapping("/colectivo/{id}")
	public ResponseEntity<Colectivo> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(colectivoService.findById(id));
	}
	
	@PostMapping("/colectivo")
	public ResponseEntity<?> getDepartamentoColectivo(@RequestBody ColectivoDepartamentoDTO colectivoDepartamentoDto){
		Colectivo colectivo = colectivoDepartamentoService.addDepartamentoColectivo(colectivoDepartamentoDto.getColectivo(), colectivoDepartamentoDto.getId_departamento());
		return ResponseEntity.ok(colectivoService.save(colectivo));
	}
	
	@GetMapping("/colectivos")
	public ResponseEntity<List<Colectivo>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(colectivoService.findAll());
	}
	
	@GetMapping("/colectivos-activos")
	public ResponseEntity<List<Colectivo>> findAllActivos(){
		return ResponseEntity.status(HttpStatus.OK).body(colectivoService.findAllActivos());
	}
	
	@PutMapping("/colectivo")
	public ResponseEntity<Colectivo> update(@RequestBody Colectivo colectivo){
		return ResponseEntity.status(HttpStatus.CREATED).body(colectivoService.save(colectivo));
	}
}
