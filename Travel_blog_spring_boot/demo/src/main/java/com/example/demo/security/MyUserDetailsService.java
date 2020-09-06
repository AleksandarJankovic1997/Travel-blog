package com.example.demo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.services.UserService;

import model.User;
import net.bytebuddy.asm.Advice.Thrown;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u=userService.getByUsername(username);
		if(u==null) {
			throw new UsernameNotFoundException("nema");
		}
		MyUserDetails myUserDetails=new MyUserDetails();
		myUserDetails.setUsername(u.getUsername());
		myUserDetails.setPassword(u.getPassword());
		myUserDetails.setRole(u.getRole());
		
		return myUserDetails;
	}

}
