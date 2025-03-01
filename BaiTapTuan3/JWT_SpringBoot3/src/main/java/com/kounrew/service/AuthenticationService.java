package com.kounrew.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kounrew.entity.User;
import com.kounrew.model.LoginUserModel;
import com.kounrew.model.RegisterUserModel;
import com.kounrew.repository.UserRepository;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationService(
				UserRepository userRepository,
				AuthenticationManager authenticationManager,
				PasswordEncoder passwordEncoder
			) {
				this.userRepository = userRepository;
				this.authenticationManager = authenticationManager; 
				this.passwordEncoder = passwordEncoder;
			}
			
	public User signup (RegisterUserModel input) {
		User user = new User();
		user.setFullName(input.getFullName()); user.setEmail(input.getEmail());
		user.setPassword (passwordEncoder.encode(input.getPassword()));
		return userRepository.save(user);
	}
	
	public User authenticate(LoginUserModel input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getEmail(), input.getPassword())
				);
		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}

}
