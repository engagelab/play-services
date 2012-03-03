package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

import play.libs.Codec;
import play.mvc.Controller;


@Entity
public class Comment extends Model {
	@ManyToOne
    @Required
    public User user;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
    @Required
	 public Task task;
	 
	public String title;
	public String message;
	
	@Required
	public Date postedAt;
	
	public Comment(User user, Project project, Task task,String title, String message){
		this.user = user;
		this.project = project;
		this.task = task;
		this.title = title;
		this.message = message;
		this.postedAt = new Date();
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

	
	public static void deleteComment(Long id) {
		Comment.delete("from Comment c where c.id=?", id);
	}

	
	public static Comment updateComment(Long id, String title, String message) {
		Comment myComment = Comment.findById(id);
		myComment.title = title;
		myComment.message = message;
		myComment.save();
		return myComment;
	}
}
