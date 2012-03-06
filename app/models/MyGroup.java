package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;

@Entity
public class MyGroup extends Model{
	
	public String name;
	public String run_id;
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<Postit> postits;
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<Comment> comments;
	
	
	//User Constructor
	public MyGroup(String name, String run_id){
		this.postits = new ArrayList<Postit>();
		this.name = name;
		this.run_id = run_id;
	}

	public Postit createPostit(Project project, Long run_id, Task task) {
		Postit newPostit = new Postit(project, run_id, this, task);
		this.postits.add(newPostit);
		this.save();
		return newPostit;
		
	}
	
	public Postit updatePostit(Long postit_id, float xpos, float ypos, String content) {
			Postit tmpPostit = Postit.findById(postit_id);
			if(tmpPostit == null)
				return null;
			tmpPostit.xpos = xpos;
			tmpPostit.ypos = ypos;
			tmpPostit.content = content;
			tmpPostit.save();
			return tmpPostit;
	}

	public Comment postComment(Project project, Long run_id, Task task) {
		Comment newComment = new Comment(project, run_id, this, task);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
	
//	public Comment updateComment(Long comment_id, String content) {
//		Comment tmpComment = Comment.findById(comment_id);
//		tmpComment.content = content;
//		tmpComment.save();
//		return tmpComment;
//	}
}
