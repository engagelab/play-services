package models;

import javax.persistence.Entity;


@Entity
public class Comment {
	
	public String title;
	public String message;
	
	public Comment(String title, String message){
		this.title = title;
		this.message = message;
	}

}
