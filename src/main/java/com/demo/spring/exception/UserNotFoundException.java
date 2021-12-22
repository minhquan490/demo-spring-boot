package com.demo.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private String value;

	public UserNotFoundException(String value) {
		super(String.format("User with value '%s' is not found", value));
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
