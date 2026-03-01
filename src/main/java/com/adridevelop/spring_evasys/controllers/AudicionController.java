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
import com.adridevelop.spring_evasys.models.Service.AudicionEmpleadoCoordinadorDepartamentoService;
import com.adridevelop.spring_evasys.models.Service.AudicionInboundServiceImpl;
import com.adridevelop.spring_evasys.models.Service.AudicionServiceImpl;
import com.adridevelop.spring_evasys.models.dto.AudicionDTO;
import com.adridevelop.spring_evasys.models.dto.AudicionInboundDto;
import com.adridevelop.spring_evasys.models.entities.Audicion;
import com.adridevelop.spring_evasys.models.entities.AudicionInbound;

@RestController
@RequestMapping("/api")
public class AudicionController {

	@Autowired
	AudicionServiceImpl audicionService;
	
	@Autowired
	AudicionInboundServiceImpl audicionInboundService;
	
	@Autowired
	AudicionEmpleadoCoordinadorDepartamentoService audicionECDS;
	
	@GetMapping("/audicion/{id}")
	public ResponseEntity<Audicion> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(audicionService.findById(id));
	}
	
	@GetMapping("/audiciones")
	public ResponseEntity<List<Audicion>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(audicionService.findAll());
	}
	
	@GetMapping("/audiciones-activas")
	public ResponseEntity<List<Audicion>> findAllActivas(){
		return ResponseEntity.status(HttpStatus.OK).body(audicionService.findAllActivos());
	}
	
	@PostMapping("/audicion/inbound")
	public ResponseEntity<AudicionInbound> createAudicionInbound(@RequestBody AudicionInboundDto audicionInboundDto){
		AudicionInbound audicionInbound = audicionInboundService.agregarInboundToAuditel(audicionInboundDto.getIdAudicion(), audicionInboundDto.getAudicionInbound());
		return ResponseEntity.status(HttpStatus.CREATED).body(audicionInboundService.save(audicionInbound));
		
	}
	
	@PutMapping("audicion")
	public ResponseEntity<Audicion> save(@RequestBody Audicion audicion){
		return ResponseEntity.status(HttpStatus.CREATED).body(audicionService.save(audicion));
	}
	
	@PostMapping("audicion")
	public ResponseEntity<Audicion> update(@RequestBody AudicionDTO audicionDto){
		Audicion audicion = audicionECDS.asignarAudicionAEmpleadoCoordinadorDepartamento(audicionDto.getAudicion(), audicionDto.getIdEmpleado(), audicionDto.getIdCoordinadorEmpleado(), audicionDto.getIdCoordinadorAuditel(), audicionDto.getIdDepartamento(), audicionDto.getIdColectivo());
		return ResponseEntity.status(HttpStatus.CREATED).body(audicionService.save(audicion));
	}
}
