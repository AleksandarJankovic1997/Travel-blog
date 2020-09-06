package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CommentPost;
import com.example.demo.services.CommentService;
import com.example.demo.services.Country_CityService;
import com.example.demo.services.UserService;
import com.example.demo.util.JwtUtil;

import model.City;
import model.Comment;
import model.User;

@RestController
@RequestMapping(value="/comment")
public class CommentController {

	@Autowired UserService userService;
	
	@Autowired JwtUtil jwtutil;
	
	@Autowired CommentService commentService;
	
	@Autowired Country_CityService countrycityService;
	
	@RequestMapping(value="/getCommentByUser",method = RequestMethod.GET,produces = "application/json")
	public List<Comment> getCommentsByUser(HttpServletRequest request){

		final String header=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		
		if(header!=null) {
			jwt=header;
			username =jwtutil.extractUsername(jwt);
		}
		User u=userService.getByUsername(username);
		return commentService.getByUser(u);
	}
	@RequestMapping(value="/getComentsByCity",method = RequestMethod.GET,produces = "application/json")
	public List<Comment> getCommentsByCity(@RequestParam("cityname")String cityname){
		return commentService.getByCityName(cityname);
	}
	@RequestMapping(value="/saveComment",consumes = "application/json",method = RequestMethod.POST)
	public ResponseEntity<?> saveComment(@RequestParam("cityname")String cityName,@RequestBody  CommentPost commentPost,HttpServletRequest request){
		final String header=request.getHeader("Authorization");
		String username=null;
		String jwt=null;
		if(header!=null) {
			jwt=header;
			username=jwtutil.extractUsername(jwt);
		}
		User u=userService.getByUsername(username);
		
		City city=countrycityService.getCityByName(cityName);
		Comment c=new Comment();
		c.setCity(city);
		c.setUser(u);
		c.setDate(new Date());
		c.setTitle(commentPost.getTitle());
		c.setText(commentPost.getText());
		if(commentService==null) {
			System.out.println("null jee");
		}
		commentService.saveComment(c);
		
		return ResponseEntity.ok().build();
	}
	
}
