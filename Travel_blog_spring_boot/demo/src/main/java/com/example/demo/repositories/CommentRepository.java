package com.example.demo.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.City;
import model.Comment;
import model.Country;
import model.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByCity(City c);
	
	List<Comment> findByUser(User u);

	//@Query("INSERT INTO Comment(Korisnik_idKorisnik, City_idCity, title, text, date) VALUES (:idkorisnik,:idcity,:title,:text,:date)")
	//void commentSave(int idkorisnik,int idcity,String title, String text, Date date);
	
	
	
}
