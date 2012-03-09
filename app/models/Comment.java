//you can use private members with getter and setter

package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import play.libs.Codec;
import play.mvc.Controller;


@Entity
public class Comment extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
	 public Task task;
	
	public Long run_id;
	
	public float xpos;
	public float ypos;
	
	@Lob
    @MaxSize(200)
    public String content;
	
	public Date postedAt;

	@PrePersist
	public void prePersist(){
		postedAt = new Date();
	}
	
	
	public Comment(Project project, Long run_id, MyGroup myGroup, Task task, String content, float xpos, float ypos) {
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		this.content = content;
		this.xpos  = xpos;
		this.ypos = ypos;
		}
	
	public static List<Comment> findByTask(Long id) {
		Task task = Task.findById(id);
		List<Comment> comments = task.comments;
		return comments;
	}
	
	
//	public static List<Comment> findByUser(Long id) {
//		User user = User.findById(id);
//		List<Comment> comments = user.comments;
//		return comments;
//	}

	
//	public static void deleteComment(Long id) {
//		Comment.delete("from Comment c where c.id=?", id);
//	}
//
//	
//	public static Comment updateComment(Long id, String title, String message) {
//		Comment myComment = Comment.findById(id);
//		return myComment;
//	}
}
