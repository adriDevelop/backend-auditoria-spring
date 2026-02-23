package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.Coordinador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinadorCentroDTO {
	private Coordinador coordinador;
	private Long idDepartamento;
	private Long idCentro;
}
