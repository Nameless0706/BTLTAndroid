package com.kounrew.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kounrew.entity.User;
import com.kounrew.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}
	
	public List<User> allUsers(){
		List<User> users = new ArrayList<>();
		
		userRepository.findAll().forEach(users::add);
		
		return users;
	}
}
