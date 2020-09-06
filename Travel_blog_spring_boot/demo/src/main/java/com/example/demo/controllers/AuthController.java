package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.security.MyUserDetailsService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import com.example.demo.util.JwtUtil;

import model.Role;
import model.User;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtutil;
	public void Register(User u) {
		
	}
	@RequestMapping(value="/register",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
	public User saveUser(@RequestBody User user) {
		Role role=roleService.getRoleById(1);
		user.setRole(role);
		user.setDatebirth(new Date());
		return userService.registerKorisnik(user);
		
	}
	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		 System.out.println("stiglo");
		 System.out.println(authenticationRequest.getPassword());
		try {
		authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(
						 authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			System.out.println("Losa sifra");
			return ResponseEntity.status(401).build();
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token=jwtutil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public void logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		System.out.println("ovde smo");
	}
	
	
}
