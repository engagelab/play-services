package models;

import java.util.List;

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
	
	public Comment(Task task,String title, String message){
		this.task = task;
		this.title = title;
		this.message = message;
	}
	
	public Comment(){
		this.title = "";
		this.message = "";
	}

	public static List<Comment> findByTask(Long id) {
		Task task = Task.findById(id);
		List<Comment> comments = task.comments;
		return comments;
	}

}
