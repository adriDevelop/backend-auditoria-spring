package com.adridevelop.spring_evasys.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

	private JWTUserDetailsService jwtUserDetailsService;
	private JWTService jwtService;
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> autenticacion(@RequestBody JWTRequest datosUsuario){
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(datosUsuario.getUsername(), datosUsuario.getPassword()));
		}catch(Exception e) {
			throw new RecursoNoEncontradoException("No se ha podido hacer correctamente el logueo del usuario.");
		}
		
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(datosUsuario.getUsername());
		
		String token = jwtService.getToken(userDetails);
		String refreshToken = jwtService.refreshToken(userDetails);
		Map<String, Object> respuestaJWT = new HashMap<>();
		
		respuestaJWT.put("message", "Login realizado con éxito");
		respuestaJWT.put("status", "200");
		
		return ResponseEntity
				.ok()
				.header("Authorizarion", "Berarer " + token)
				.body(new JWTResponse(token, refreshToken, respuestaJWT));
	}
	
	@PostMapping("/regenerarToken")
	public ResponseEntity<?> regenerarToken(@RequestBody String tokenRecibido){

		String tokenRecogido = tokenRecibido;
		String subject = this.jwtService.getUsernameClaim(tokenRecogido);
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(subject);
		
		if(!this.jwtService.checkTokenValidity(tokenRecogido, userDetails)) {
			throw new RecursoNoEncontradoException("El token está caducado, tienes que volver a iniciar sesión");
		}
		
		String token = jwtService.getToken(userDetails);
		String refreshToken = jwtService.refreshToken(userDetails);
		
		Map<String, Object> respuestaJWT = new HashMap<>();
		
		respuestaJWT.put("message", "Token regenerado con éxito");
		respuestaJWT.put("status", "200");
		
		return ResponseEntity
				.ok()
				.header("Authorizarion", "Berarer " + token)
				.body(new JWTResponse(token, refreshToken, respuestaJWT));
	}
	
	
}
