package com.adridevelop.spring_evasys.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTRequest {
	
	private String username;
	private String password;

}
