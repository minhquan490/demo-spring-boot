package com.demo.spring.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
public class ApplicationExceptionHandler {

	@AfterThrowing(pointcut = "target(com.demo.spring.service.UserService)", throwing = "e")
	public ResponseEntity<String> usernameNotFoundExceptionHandler(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@AfterThrowing(pointcut = "target(com.demo.spring.service.UserService)", throwing = "e")
	public ResponseEntity<String> userAlreadyExistExceptionHandler(UserAlreadyExistException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}