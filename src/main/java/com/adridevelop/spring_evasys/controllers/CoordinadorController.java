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

import com.adridevelop.spring_evasys.models.Service.CoordinadorDepartamentoService;
import com.adridevelop.spring_evasys.models.Service.CoordinadorServiceImpl;
import com.adridevelop.spring_evasys.models.Service.DepartamentoCentroService;
import com.adridevelop.spring_evasys.models.dto.CoordinadorCentroDTO;
import com.adridevelop.spring_evasys.models.dto.CoordinadorCentroUpdateDTO;
import com.adridevelop.spring_evasys.models.dto.CoordinadorPasswordDTO;
import com.adridevelop.spring_evasys.models.entities.Coordinador;

@RestController
@RequestMapping("/api")
public class CoordinadorController {
	
	// Hay que inyectar el service
	@Autowired
	CoordinadorServiceImpl coordinadorService;
	
	@Autowired
	CoordinadorDepartamentoService coordinadorDepartamentoService;
	
	@Autowired
	DepartamentoCentroService departamentoCentroService;
	
	// Aquí van los métodos y las rutas para la llamada a los métodos que son necesarios para realizar las operaciones CRUD y las peticiones
	
	// Método que devuelve en la ruta /api/coordinadores un listado de coordinadores
	@GetMapping("/coordinadores")
	public ResponseEntity<List<Coordinador>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.findAll());
	}
	
	// Método que devuelve el coordinador con la id pasada por parametro
	@GetMapping("/coordinador/{id}")
	public ResponseEntity<Coordinador> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.findById(id));
	}
	
	@GetMapping("/coordinador/{idDepartamento}/{idCentro}")
	public ResponseEntity<List<Coordinador>> findCoordinadoresCentro(@PathVariable Long idDepartamento, @PathVariable Long idCentro){
		return ResponseEntity.ok(departamentoCentroService.getCoordinadorCentroDepartamento(idCentro, idDepartamento));
	}
	
	@GetMapping("/coordinadores-activos")
	public ResponseEntity<List<Coordinador>> findAllActivos(){
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.findAllActivos());
	}
	
	@GetMapping("/coordinadores-nombre/{nombreUsuario}")
	public ResponseEntity<List<Coordinador>> findCoordinadoresByNombreOrUsuario(@PathVariable(required = false) String nombreUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.findByNombreOrUsuario(nombreUsuario));
	}
	
	// Método que crea el coordinador pasado en la base de datos
	@PostMapping("/coordinador")
	public ResponseEntity<?> save(@RequestBody CoordinadorCentroDTO coordinadorDepartamentoDto){
		Coordinador coordinador = coordinadorDepartamentoService.addNewCoordinador(coordinadorDepartamentoDto.getIdCentro(), coordinadorDepartamentoDto.getIdDepartamento(), coordinadorDepartamentoDto.getCoordinador());
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.save(coordinador));
	}
	
	// Método que actualiza el coordinador pasado en la base de datos
	@PutMapping("/coordinador-password")
	public ResponseEntity<?> update(@RequestBody CoordinadorPasswordDTO coordinadorPasswordDto){
	     Coordinador coordinador = coordinadorService.cambiarContrasena(coordinadorPasswordDto.getIdCoordinador(), coordinadorPasswordDto.getPassword());
	     return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.save(coordinador));
	}
	
	// Método que actualiza el coordinador pasado en la base de datos
	@PutMapping("/coordinador")
	public ResponseEntity<?> update(@RequestBody Coordinador coordinador){
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.save(coordinador));
	}
	
	// Método que actualiza el coordinador pasado en la base de datos
	@PutMapping("/coordinador-centro")
	public ResponseEntity<?> update(@RequestBody CoordinadorCentroUpdateDTO coordinadorDepartamentoDto){
		Coordinador coordinador = coordinadorDepartamentoService.addCoordinadorToCentro(coordinadorDepartamentoDto.getIdCentro(), coordinadorDepartamentoDto.getIdDepartamento(), coordinadorDepartamentoDto.getCoordinador());
		return ResponseEntity.status(HttpStatus.OK).body(coordinadorService.save(coordinador));
	}
}
