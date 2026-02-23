package com.adridevelop.spring_evasys.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adridevelop.spring_evasys.models.Service.EmpleadoCoordinadorService;
import com.adridevelop.spring_evasys.models.Service.EmpleadoServiceImpl;
import com.adridevelop.spring_evasys.models.dto.EmpleadoCoordinadorDTO;
import com.adridevelop.spring_evasys.models.entities.Empleado;

@RestController
@RequestMapping("/api")
public class EmpleadosController {
	
	@Autowired
	EmpleadoServiceImpl empleadoService;
	
	@Autowired
	EmpleadoCoordinadorService empleadoCoordinadorService;
	
	@GetMapping("/empleado/{id}")
	public ResponseEntity<Empleado> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findById(id));
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<List<Empleado>> getEmpleadosByCoordinador(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findEmpleadosByCoordinador(id));
	}
	
	@GetMapping("/empleado-nombre/{nombre}")
	public ResponseEntity<List<Empleado>> findByNombre(@PathVariable String nombre){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findByNombre(nombre));
	}
	
	@GetMapping("/empleado-coordinador-nombre/{idCoordinador}/{nombre}")
	public ResponseEntity<List<Empleado>> findEmpleadosCoordinador(@PathVariable Long idCoordinador, @PathVariable String nombre){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoCoordinadorService.getEmpleadosCoordinadorNombre(idCoordinador, nombre));	
	}
	
	@GetMapping("/empleados")
	public ResponseEntity<List<Empleado>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findAll());
	}
	
	@GetMapping("/empleados-activos")
	public ResponseEntity<List<Empleado>> findAllActivos(){ 
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findAllActivos());
	}
	
	@PostMapping("/empleado")
	public ResponseEntity<Empleado> save(@RequestBody EmpleadoCoordinadorDTO empleadoCoordinadorDto){
		Empleado empleado = empleadoCoordinadorService.asignEmpleadoToCoordinadorAndDepartment(empleadoCoordinadorDto.getIdCoordinador(), empleadoCoordinadorDto.getIdDepartamento(), empleadoCoordinadorDto.getEmpleado());
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.save(empleado));
	}
	
	@PutMapping("/empleado")
	public ResponseEntity<Empleado> update(@RequestBody Empleado empleado){
		return ResponseEntity.status(HttpStatus.OK).body(empleadoService.save(empleado));
	}
	
	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		empleadoService.deleteById(id);
		return ResponseEntity.ok(Map.of("message", "Eliminado correctamente"));
	}
	
	
}
