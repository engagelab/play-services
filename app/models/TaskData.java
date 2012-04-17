package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * TaskData entity managed by JPA
 */
@Entity
public class TaskData extends Model{
	
	@ManyToOne
    public Groupp groupp;
	
	@ManyToOne
	@Required
	 public Project project;
	
	@ManyToOne
	public Task task;
	
	public Long run_id;
	
	public String taskdata;
	
	@PrePersist
	public void prePersist(){
		taskdata = "1000";
	}
	
	public TaskData(Project project, Long run_id, Groupp groupp, Task task) {
		this.project = project;
		this.run_id = run_id;
		this.groupp = groupp;
		this.task = task;
		}
}


