package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

import play.mvc.Controller;


@Entity
public class Comment extends Model {
	@ManyToOne
    @Required
    public User user;
	
	@ManyToOne
    @Required
	 public Task task;
	
	public String title;
	public String message;
	
	public Comment(User user, Task task,String title, String message){
		this.user = user;
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
	
	public static List<Comment> findByUser(Long id) {
		User user = User.findById(id);
		List<Comment> comments = user.comments;
		return comments;
	}
}
