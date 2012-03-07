package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Variables_Data extends Model {
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
	 public Task task;
	
	public Long run_id;
	
	public String s1_meter;
	
	public Variables_Data(Project project, Long run_id, MyGroup myGroup, Task task, String s1_meter){
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		this.s1_meter = s1_meter;
	}
}
