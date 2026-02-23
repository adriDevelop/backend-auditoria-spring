package com.adridevelop.spring_evasys.security;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {
	
	private String jwt;
	private String refreshToken;
	private Map<String, Object> messages;

}
