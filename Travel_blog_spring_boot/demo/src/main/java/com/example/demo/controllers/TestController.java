package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.RoleService;

@RestController
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	RoleService roleService;
	
	
	@RequestMapping(value="/hello",method=RequestMethod.POST,produces = "application/json")
	public String hello(@RequestBody String tekst) {
		return "tekst";
	}
	
	@RequestMapping(value="/hello2",method = RequestMethod.GET,produces = "appication/json")
	public Date hello2() {
		return new Date();
	}
	
	@RequestMapping(value="/getUloga",method=RequestMethod.GET,produces="application/json")
	public String getUloga() {
		return roleService.getRoleById(1).getName();
	}
}
