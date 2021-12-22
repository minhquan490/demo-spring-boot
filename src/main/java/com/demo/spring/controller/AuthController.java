package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.model.User;
import com.demo.spring.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) throws Exception {
		if (user == null) {
			return ResponseEntity.badRequest().body("username, password and email can not be null");
		}
		if (user.getUsername().isBlank() || user.getEmail().isBlank()) {
			return ResponseEntity.badRequest().body("Username or email can not be empty");
		}
		if (user.getPassword().isBlank()) {
			return ResponseEntity.badRequest().body("Password can not be empty");
		}
		user.setPremiumEnable(false);

		return userService.register(user);
	}

}