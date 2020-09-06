package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.repositories.UserRepository;

import model.User;
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User registerKorisnik(User u) {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return  userRepository.save(u);
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).get();
	
	}
	public User saveUser(User u) {
		return userRepository.save(u);
	}
	
}
