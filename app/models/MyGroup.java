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
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<Variables_Data> variables_datum;
	
	
	//User Constructor
	public MyGroup(String name, String run_id){
		this.comments = new ArrayList<Comment>();
		this.variables_datum = new ArrayList<Variables_Data>();
		this.name = name;
		this.run_id = run_id;
	}

	public Comment postComment(Project project, Long run_id, Task task, String content, float xpos, float ypos ) {
		Comment newComment = new Comment(project, run_id, this, task, content, xpos, ypos);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}

	public Variables_Data saveVariableData(Project project, Long run_id,
			Task task, String s1_meter) {
		
		Variables_Data var = new Variables_Data(project, run_id, this, task,s1_meter);
		this.variables_datum.add(var);
		this.save();
		return var;
	}
	
}
