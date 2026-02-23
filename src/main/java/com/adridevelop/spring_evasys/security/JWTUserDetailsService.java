package com.adridevelop.spring_evasys.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adridevelop.spring_evasys.exceptions.RecursoNoEncontradoException;
import com.adridevelop.spring_evasys.models.Dao.CoordinadorDao;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private CoordinadorDao coordinadorDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return coordinadorDao.findOneByUsuario(username)
				.map(c -> {
					
					List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(c.getRol()));
					
					return new User(c.getUsuario(), c.getPassword(), authorities);
					
				}).orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado al usuario"));
	}
}
