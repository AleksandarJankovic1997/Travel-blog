package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.City;
import model.Country;

public interface CityRepository extends JpaRepository<City, Long>{
	List<City> findByCountry(Country c);
	
	City findByName(String name);
}
