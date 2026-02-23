package com.adridevelop.spring_evasys.models.dto;

import java.util.List;

import com.adridevelop.spring_evasys.models.entities.Centro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentroDTO {
	String nombre;
	String localizacion;
	List<Long> idsDepartamentos;
}
