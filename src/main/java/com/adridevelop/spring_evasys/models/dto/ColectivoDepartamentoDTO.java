package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.Colectivo;

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
public class ColectivoDepartamentoDTO {

	private Colectivo colectivo;
	private Long id_departamento;
	
}
