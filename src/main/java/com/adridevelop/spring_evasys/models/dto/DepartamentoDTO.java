package com.adridevelop.spring_evasys.models.dto;

import java.util.List;

import com.adridevelop.spring_evasys.models.entities.Departamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartamentoDTO {
	
	Departamento departamento;
	List<Long> idColectivos;
}
