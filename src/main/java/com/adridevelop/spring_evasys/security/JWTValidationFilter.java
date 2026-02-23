package com.adridevelop.spring_evasys.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.exceptions.TokenCaducadoException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTValidationFilter extends OncePerRequestFilter{

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private JWTUserDetailsService jwtUserDetailsService;
	
	@Autowired
    @Qualifier("handlerExceptionResolver") // Inyectamos el resolver de Spring
    private HandlerExceptionResolver resolver;
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
		String username = null;
		String jwt = null;
		
		
		try {
			if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(AUTHORIZATION_HEADER_BEARER)) {
				jwt = requestTokenHeader.substring(AUTHORIZATION_HEADER_BEARER.length());
				try {
					username = jwtService.getUsernameClaim(jwt);
				}catch(Exception e) {
					resolver.resolveException(request, response, null, new TokenCaducadoException("El token está caducado"));
				}
				
				if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
					UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
					
					if (jwtService.checkTokenValidity(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(upaToken);			
					}
				}
			}
			
			filterChain.doFilter(request, response);
			
		}catch(Exception ex) {
			resolver.resolveException(request, response, null, ex);
		}
	}
}
