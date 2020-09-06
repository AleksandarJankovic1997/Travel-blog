package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Country;

public interface CountryRepository extends JpaRepository<Country,Long> {
	Country findByName(String name);
}
