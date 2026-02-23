package com.adridevelop.spring_evasys.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidation) throws Exception{
		http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests( auth -> {
			
			auth.requestMatchers(HttpMethod.GET, "/api/audiciones-activas").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/audiciones").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/audicion/**").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/audicion").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/audicion").hasAnyRole("ADMIN");
			
			auth.requestMatchers(HttpMethod.GET, "/api/centros-activos").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/centros").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/centro/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/centro-nombre/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/addDepartamentoToCentro").hasAnyRole("SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/centro").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/centro").hasAnyRole("ADMIN");
			
			auth.requestMatchers(HttpMethod.GET, "/api/colectivos-activos").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/colectivos").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/colectivo/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/colectivo").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/colectivo").hasAnyRole("ADMIN");
			
			auth.requestMatchers(HttpMethod.GET, "/api/coordinadores-activos").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/coordinadores").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/coordinadores-nombre/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/coordinador/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/coordinador").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/coordinador").hasAnyRole("ADMIN", "SUPER_ADMIN");
			
			auth.requestMatchers(HttpMethod.GET, "/api/departamentos-activos").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/departamentos").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/departamento-nombre/**").hasAnyRole("SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/departamento").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/departamento").hasAnyRole("ADMIN", "SUPER_ADMIN");
			
			auth.requestMatchers(HttpMethod.GET, "/api/empleados-activos").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/empleados").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/empleados/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/empleado/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/empleado-nombre/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/empleado-coordinador-nombre/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.DELETE, "/api/empleado/**").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/api/empleado").hasAnyRole("ADMIN", "SUPER_ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/empleado").hasAnyRole("ADMIN", "SUPER_ADMIN");
			
			
			
			auth.anyRequest().permitAll();
		});
		
		http.addFilterAfter(jwtValidation, BasicAuthenticationFilter.class);
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		config.setAllowedHeaders(List.of("*"));
		
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", config);
		
		return corsConfigurationSource;
	}

}
