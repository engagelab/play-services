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
	
	public Long run_id;

	@Lob
    @MaxSize(200)
    public String content;
	
	public float xpos;
	public float ypos;
	public Date postedAt;

	@PrePersist
	public void prePersist(){
		postedAt = new Date();
	}
	
	public Postit(Project project, Long run_id, MyGroup myGroup, Task task) {
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.content = "";
		this.xpos = 0.0f;
		this.ypos = 0.0f;
		}
	
	public Postit(Project project, Long run_id, MyGroup myGroup, Task task, float xpos, float ypos, String content) {
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		this.content = content;
		this.xpos = xpos;
		this.xpos = ypos;
		}
}
