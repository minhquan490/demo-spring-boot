package com.demo.spring.exception;

@SuppressWarnings("serial")
public class InvalidUserCredentialsException extends RuntimeException {

	public InvalidUserCredentialsException(String msg) {
		super(msg);
	}
}
