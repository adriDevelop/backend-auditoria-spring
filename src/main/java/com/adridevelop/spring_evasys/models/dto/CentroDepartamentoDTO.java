package com.adridevelop.spring_evasys.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentroDepartamentoDTO {
	
	private Long idCentro;
	private String nombre;
	private String localizacion = "";
	private List<Long> idsDepartamentos;

}
