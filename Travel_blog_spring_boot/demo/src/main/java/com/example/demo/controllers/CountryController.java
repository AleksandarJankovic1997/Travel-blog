package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.Country_CityService;

import model.Country;

@RestController
@RequestMapping(value="/country")
public class CountryController {
	
	@Autowired
	Country_CityService countrycityService;
	
	@RequestMapping(value="/getAll",method = RequestMethod.GET, produces = "application/json")
	public List<Country> getAllCountries(){
		return this.countrycityService.getAllCountries();
	}
	

}
