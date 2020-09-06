package com.example.demo.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository  extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
	
	Optional<User> findByUsernameAndPassword(String username, String password);
	
	Boolean existsByUsername(String username);


}
