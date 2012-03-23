package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class TaskData extends Model{
	
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
	//@Required  to avoid the project id
	 public Project project;
	
	@ManyToOne
	public Task task;
	
	public Long run_id;
	
	public String taskdata;
	
	@PrePersist
	public void prePersist(){
		taskdata = "1000";
	}
	
	public TaskData(Project project, Long run_id, MyGroup myGroup, Task task) {
		this.project = project;
		this.run_id = run_id;
		this.myGroup = myGroup;
		this.task = task;
		}
}

//	public static TaskData findByTaskAndGroup(Task task, MyGroup group) {
//		TaskData data = TaskData.find("byTaskandMyGroup",task,group);
//		return data;
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


