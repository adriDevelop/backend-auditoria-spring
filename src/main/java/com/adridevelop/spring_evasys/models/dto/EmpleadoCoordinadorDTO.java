package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoCoordinadorDTO {
	private Empleado empleado;
	private Long idCoordinador;
	private Long idDepartamento;
}
