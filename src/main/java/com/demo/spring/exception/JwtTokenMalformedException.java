package com.demo.spring.exception;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class JwtTokenMalformedException extends AuthenticationException {

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}

}
