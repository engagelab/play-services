package models;

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
    @Required
	 public Project project;
	
	@ManyToOne
    @Required
	 public Task task;
	 
	@Required
	public String uid;
	
	public String title;
	public String message;
	
	public Comment(User user, Project project, Task task,String title, String message){
		this.user = user;
		this.project = project;
		this.task = task;
		this.uid = Codec.UUID();
		this.title = title;
		this.message = message;
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

	
	public static void deleteComment(String uid) {
		Comment.delete("from Comment c where c.uid=?", uid);
	}

	
	public static Comment updateComment(String uid, String title, String message) {
		Comment myComment = Comment.find("uid", uid).first();
		myComment.title = title;
		myComment.message = message;
		myComment.save();
		return myComment;
	}
}
