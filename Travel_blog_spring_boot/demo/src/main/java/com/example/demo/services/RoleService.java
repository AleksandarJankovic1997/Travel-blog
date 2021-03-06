package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.RoleRepository;

import model.Role;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role getRoleById(Integer idUloga) {
		return roleRepository.findByIdRole(idUloga);
	}
}
