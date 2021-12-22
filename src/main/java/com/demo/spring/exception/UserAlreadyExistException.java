package com.demo.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException {

	private String username;

	public UserAlreadyExistException(String username) {
		super(String.format("'%s' is already exist", username));
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
