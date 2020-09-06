package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.CountryRepository;

import model.City;
import model.Country;

@Service
public class Country_CityService {
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	public List<Country> getAllCountries(){
		return countryRepository.findAll();
	}
	public List<City> getAllCitiesByCountry(String name){
		
		Country c=countryRepository.findByName(name);
		System.out.println(c.toString());
		return cityRepository.findByCountry(c);
	}
	public City getCityByName(String city) {
		City c=cityRepository.findByName(city);
		
		return c;
	}
	
	
}
