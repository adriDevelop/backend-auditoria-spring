package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.Audicion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AudicionDTO {
	
	private Audicion audicion;
	private Long idEmpleado;
	private Long idCoordinadorEmpleado;
	private Long idCoordinadorAuditel;
	private Long idDepartamento;
	private Long idColectivo;

}
