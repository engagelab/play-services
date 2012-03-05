package models;

import play.*;
import play.data.validation.*;
import play.db.jpa.*;
import play.libs.Codec;

import javax.persistence.*;

import java.util.*;

@Entity
public class Postit extends Model {
	
	//{"scene":"","_id":"4f3e3bd09c95052c17000004","ypos":"","xpos":"","content":"","user":""}
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
	 public Task task;

	@Lob
    @MaxSize(200)
    public String content;
	
	public float xpos;
	public float ypos;
	public Date postedAt;

//	@PrePersist
//	public void prePersist(){
//		postedAt = new Date();
//	}
	
	public Postit(MyGroup myGroup, Project project,  Task task) {
		this.myGroup = myGroup;
		this.project = project;
		this.task = task;
		this.content = "";
		this.xpos = 0.0f;
		this.ypos = 0.0f;
		}
	
	public Postit(MyGroup myGroup, Project project, Task task, float xpos, float ypos, String content) {
		this.myGroup = myGroup;
		this.project = project;
		this.task = task;
		this.content = content;
		this.xpos = xpos;
		this.xpos = ypos;
		}
}
