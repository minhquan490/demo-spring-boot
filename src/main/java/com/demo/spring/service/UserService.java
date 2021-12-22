package com.demo.spring.service;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.spring.detail.UserDetail;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.model.User;

public interface UserService extends UserDetailsService {

	void save(User user) throws HibernateException;

	void edit(User user, long id) throws Exception;

	void delete(User user) throws HibernateException;

	Optional<User> get(long id) throws UserNotFoundException;

	User login(String username, String password);

	UserDetail getUserDetailByUsername(String username);

	ResponseEntity<?> register(User user);
}