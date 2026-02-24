package com.adridevelop.spring_evasys.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinadorPasswordDTO {
	private Long idCoordinador;
    private String password;
}
