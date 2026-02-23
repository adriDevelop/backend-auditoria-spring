package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.Departamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoCentroDTO {
	
	private Departamento departamento;
	private Long idCentro;

}
