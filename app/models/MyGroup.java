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
    public List<Comment> comments;
	
	
	//User Constructor
	public MyGroup(String name, String run_id){
		this.comments = new ArrayList<Comment>();
		this.name = name;
		this.run_id = run_id;
	}

	public Comment postComment(Project project, Long run_id, Task task) {
		Comment newComment = new Comment(project, run_id, this, task);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
	
}
