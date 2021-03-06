package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.Country_CityService;

import model.City;

@RestController
@RequestMapping(value="/city")
public class CityController {
	
	@Autowired
	Country_CityService countrycityService;
	
	@RequestMapping(value="/getAllByCountry",method = RequestMethod.GET,produces = "application/json")
	public List<City> getAllCitiesByCountry(@RequestParam("countryname")String countryname){
		return this.countrycityService.getAllCitiesByCountry(countryname);
	}
	@RequestMapping(value = "/getCityByName",method = RequestMethod.GET,produces = "application/json")
	public City getCityByName(@RequestParam("cityname") String cityname) {
		return countrycityService.getCityByName(cityname);
	}
	
}
