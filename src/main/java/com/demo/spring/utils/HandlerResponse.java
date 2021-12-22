package com.demo.spring.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HandlerResponse {

	public static ResponseEntity<?> responseEntity(Object body, HttpStatus httpCode) {
		return new ResponseEntity<>(body, httpCode);
	}
}
