package com.demo.spring.exception;

@SuppressWarnings("serial")
public class DisabledUserException extends RuntimeException {

	public DisabledUserException(String msg) {
		super(msg);
	}
}
