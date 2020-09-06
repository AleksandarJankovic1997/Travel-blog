package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ChangePassModel;
import com.example.demo.services.UserService;
import com.example.demo.util.JwtUtil;

import model.User;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	JwtUtil jwtutil;
	
	@RequestMapping(value="/changeInfos",method = RequestMethod.GET,produces = "application/json")
	public boolean changeInfos(@RequestParam("city") String city,@RequestParam("country") String country,
			@RequestParam("first_name")String first_name,@RequestParam("last_name") String last_name,
			HttpServletRequest request) {
		
		final String header=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		
		if(header!=null) {
			jwt=header;
			username =jwtutil.extractUsername(jwt);
		}
		User u=userService.getByUsername(username);
		if(!city.equalsIgnoreCase("")) {
			u.setCity(city);
		}
		if(!country.equalsIgnoreCase("")) {
			u.setCountry(country);
		}
		if(!first_name.equalsIgnoreCase("")) {
			u.setFirstName(first_name);
		}
		if(!last_name.equalsIgnoreCase("")) {
			u.setLastName(last_name);
		}
		userService.saveUser(u);
		return true;
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET, produces = "application/json")
	public User getUser(HttpServletRequest request) {
		final String header=request.getHeader("Authorization");
		String username=null;
		String jwt=null;
		if(header!=null) {
			jwt=header;
			username=jwtutil.extractUsername(jwt);
		}
		User u=userService.getByUsername(username);
		return u;
	}
	@RequestMapping(value = "/hello",method = RequestMethod.POST)
	public void hello(@RequestBody String hello) {
		System.out.println(hello);
	}
	
	@RequestMapping(value="/changePass",method = RequestMethod.POST, consumes ="application/json",produces = "application/json")
	public ResponseEntity<?> changePass(@RequestBody ChangePassModel changePassModel,HttpServletRequest request) {
		final String header=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		
		if(header!=null) {
			jwt=header;
			username =jwtutil.extractUsername(jwt);
		}
		User u=userService.getByUsername(username);
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
		if(!passwordEncoder.matches(changePassModel.getOldpassword(),u.getPassword())) {
			return ResponseEntity.badRequest().build();
			
		}else {
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			String newpassword=bCryptPasswordEncoder.encode(changePassModel.getNewpassword());
			u.setPassword(newpassword);
		}
		userService.saveUser(u);
		return ResponseEntity.ok(u);
	}
	@RequestMapping(value="/isLoggedIn", method = RequestMethod.GET)
	public boolean isLoggedIn() {
		System.out.println("aa");
		return true;
	}
	
	 
	
	
}
