package com.demo.spring.exception;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class JwtTokenMissingException extends AuthenticationException {

	public JwtTokenMissingException(String msg) {
		super(msg);
	}

}
