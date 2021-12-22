package com.demo.spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.spring.detail.UserDetail;
import com.demo.spring.exception.ResourceNotFoundException;
import com.demo.spring.exception.UserAlreadyExistException;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.model.Role;
import com.demo.spring.model.User;
import com.demo.spring.repository.UserRepository;
import com.demo.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void edit(User user, long id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		existingUser.setAvatar(user.getAvatar());
		existingUser.setCountry(user.getCountry());
		existingUser.setPassword(user.getPassword());
		existingUser.setPremiumEnable(user.isPremiumEnable());
		userRepository.save(existingUser);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public Optional<User> get(long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found.");
		}
		Set<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public User login(String username, String password) {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("Username does not exist");
		}
		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			return null;
		}
		return user;
	}

	@Override
	public ResponseEntity<?> register(User user) {
		if (userRepository.getUserByUsername(user.getUsername()) != null
				|| userRepository.getUserByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistException(user.getUsername());
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		this.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Register success");
	}

	@Override
	public UserDetail getUserDetailByUsername(String username) {
		User user = userRepository.getUserByUsername(username);

		if (user != null) {
			Set<String> roleNames = user.getRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toSet());

			UserDetail userDetail = new UserDetail();
			userDetail.setUsername(user.getUsername());
			userDetail.setUserPassword(user.getPassword());
			userDetail.setRoles(roleNames);

			return userDetail;
		}
		return null;
	}
}