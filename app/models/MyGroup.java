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
	
	
	//User Constructor
	public MyGroup(String name, String run_id){
		this.postits = new ArrayList<Postit>();
		this.name = name;
		this.run_id = run_id;
	}

	public Postit createPostit(Project project, Task task) {
		Postit newPostit = new Postit(this, project,task);
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

}
