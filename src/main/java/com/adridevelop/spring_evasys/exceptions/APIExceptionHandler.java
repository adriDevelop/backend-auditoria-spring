package com.adridevelop.spring_evasys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class APIExceptionHandler {
	
	private ResponseEntity<ManejoErroresDTO> build(HttpStatus status, Exception ex, HttpServletRequest req){
		
		ManejoErroresDTO error = new ManejoErroresDTO(
														status.value(), // Retorna el valor del error en un Integer. El código HTTP Response.
														status.getReasonPhrase(), // Retorna el porque ha sucedido el error.
														ex.getMessage(), // Retorna el mensaje de la excepción que ha saltado.
														req.getRequestURI() // Retorna la url que ha lanzado el error
		);
		
		return new ResponseEntity<ManejoErroresDTO>(error, status);
	}
	
	@ExceptionHandler(RecursoNoEncontradoException.class)
	public ResponseEntity<ManejoErroresDTO> recursoNoEncontradoException(RecursoNoEncontradoException ex, HttpServletRequest req) {
		return this.build(HttpStatus.NOT_FOUND, ex, req);
	}
	
	@ExceptionHandler(TokenCaducadoException.class)
	public ResponseEntity<ManejoErroresDTO> tokenCaducado(TokenCaducadoException ex, HttpServletRequest req) {
		return this.build(HttpStatus.FORBIDDEN, ex, req);
	}
	
	@ExceptionHandler(RecursoYaExistenteException.class)
	public ResponseEntity<ManejoErroresDTO> recursoYaExistenteException(RecursoYaExistenteException ex, HttpServletRequest req) {
		return this.build(HttpStatus.BAD_REQUEST, ex, req);
	}
}
