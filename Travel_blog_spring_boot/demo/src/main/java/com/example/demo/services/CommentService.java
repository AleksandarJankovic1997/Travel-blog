package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.CommentRepository;

import model.City;
import model.Comment;
import model.User;

@Service
public class CommentService {

	@Autowired CommentRepository commentRepository;
	
	@Autowired CityRepository cityRepository;
	
	public List<Comment> getByUser(User u){
		return commentRepository.findByUser(u);
	}
	public List<Comment> getByCityName(String cityname){
		City city=cityRepository.findByName(cityname);
		return commentRepository.findByCity(city);
	}
	public void saveComment(Comment c) {
		commentRepository.save(c);
		//commentRepository.commentSave(c.getUser().getIdKorisnik(),c.getCity().getIdCity(), c.getTitle(), c.getText(),c.getDate());
	}
	
}
