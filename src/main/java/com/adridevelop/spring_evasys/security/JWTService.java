package com.adridevelop.spring_evasys.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.exceptions.TokenCaducadoException;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;
import com.adridevelop.spring_evasys.models.entities.Coordinador;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	// Inyectamos el DAO del usuario que nos hará falta para poder obtener los datos de la bbdd
	@Autowired
	private CoordinadorDao coordinadorDao;
	
	// Ahora, creamos una constante estática en la que vamos a almacenar el tiempo que va a estar el token activo. Este tiempo está en segundos.
	public static final Long JWT_TOKEN_VALIDITY = 1l;
	public static final Long JWT_REFRESH_TOKEN_VALIDITY = 86400000l;
	
	// Otra constante estática en la que vamos a almacenar el secreto de jwt. Con lo que vamos a firmar el jwt.
	public static final String JWT_SECRET = "LUNDFKEISOK32JH86JNVJF8JGDFSGsdf0DFGSDFEi9";
	
	// Método que devuelve todos los claims de un token.
	public Claims getAllClaimsFromToken(String token) {
		
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	// Método que devuelve el claim que nosotros queramos obtener de los claims.
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
		
	}
	
	// Método para obtener la fecha de caducidad del token.
	private Date getTokenExpirationDate(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	// Método para comprobar si el token ha caducado.
	public boolean tokenExpired(String token) {
		return getTokenExpirationDate(token).before(new Date());
	}
	
	// Método que devuelve el nombre de usuario del token.
	public String getUsernameClaim(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// Método que devuelve si el token es válido.
	public boolean checkTokenValidity(String token, UserDetails userDetails) {
		
		String usernameFromClaims = getUsernameClaim(token);
		String usernameBBDD = userDetails.getUsername();
		
		boolean tokenExpired = tokenExpired(token);
		
		return usernameFromClaims.equals(usernameBBDD) && !tokenExpired;
		
	}
	
	// Método que genera el token para el usuario.
	public String generateToken(Map<String, Object> claims, String subject, Long validity) {
		
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validity * 60 * 1000))
				.signWith(key)
				.compact();
	}
	
	public String refreshToken(UserDetails userDetails) {
		
		Coordinador coordinador = coordinadorDao.findOneByUsuario(userDetails.getUsername()).orElseThrow(()-> new TokenCaducadoException("No se ha encontrado a un usuario con ese nombre de usuario"));
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("ROL", userDetails.getAuthorities());
		claims.put("nombre", coordinador.getNombre());
		claims.put("apellidos", coordinador.getApellidos());
		claims.put("departamento", coordinador.getDepartamento());
		claims.put("id", coordinador.getId_coordinador());
		
		return generateToken(claims, coordinador.getUsuario(), JWT_REFRESH_TOKEN_VALIDITY);
	}
	
	// Método que obtiene el token.
	public String getToken(UserDetails userDetails) {
		
		Coordinador coordinador = coordinadorDao.findOneByUsuario(userDetails.getUsername()).orElseThrow(()-> new TokenCaducadoException("No se ha encontrado a un usuario con ese nombre de usuario"));
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("ROL", userDetails.getAuthorities());
		claims.put("nombre", coordinador.getNombre());
		claims.put("apellidos", coordinador.getApellidos());
		claims.put("departamento", coordinador.getDepartamento());
		claims.put("id", coordinador.getId_coordinador());
		
		return generateToken(claims, coordinador.getUsuario(), JWT_TOKEN_VALIDITY);
	}
	
}
