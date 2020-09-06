package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idComment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int numberLikes;

	private String text;

	private String title;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="City_idCity")
	private City city;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_idKorisnik")
	private User user;

	public Comment() {
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberLikes() {
		return this.numberLikes;
	}

	public void setNumberLikes(int numberLikes) {
		this.numberLikes = numberLikes;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}