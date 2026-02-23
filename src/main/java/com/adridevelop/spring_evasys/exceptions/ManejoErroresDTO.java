package com.adridevelop.spring_evasys.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
// Clase manejadora que devolverá el mensaje de error en todas nuestras excepciones.
public class ManejoErroresDTO {
	
	private LocalDateTime momentoDelError; // Mostraremos el momento en el que ha dado el error con fecha y hora.
	private int status;					   // Mostraremos el status de la petición HTTP.
	private String error;				   // Mostraremos el error que ha ocurrido en la petición.
	private String message;				   // Mostraremos el mensaje de error.
	private String path;				   // Mostraremos la ruta que ha dado el error.
	
	public ManejoErroresDTO(int status, String error, String message, String path) {
		this.momentoDelError = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
