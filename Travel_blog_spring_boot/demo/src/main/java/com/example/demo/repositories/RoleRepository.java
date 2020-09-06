package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String nazivUloga);
	
	Role findByIdRole(int idRole);
	
	
	
}
