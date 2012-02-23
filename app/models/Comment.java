package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Comment extends Model {
	
	public String title;
	public String message;
	
	@ManyToOne
    @Required
	 Task task;
	
	public Comment(String title, String message){
		this.title = title;
		this.message = message;
	}

}
