package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class TaskData extends Model {
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
	 public Task task;
	
	public Long run_id;
	
	public String data;
	
	public TaskData(Project project, Long run_id, MyGroup myGroup, Task task){
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		this.data = "";
	}
	
	public TaskData(Project project, Long run_id, MyGroup myGroup, Task task, String data){
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		this.data = data;
	}
}
