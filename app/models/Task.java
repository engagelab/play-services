package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Task extends Model{
	 public String title;
	 public String data;
	 
	 @OneToMany(mappedBy="task", cascade=CascadeType.ALL)
	 public List<Comment> comments;
	 
	 @OneToMany(mappedBy="task", cascade=CascadeType.ALL)
	 public List<TaskData> taskdata;
	 
	 @ManyToOne
	 @Required
	 public Scene scene;
	 
	 public Task(String title){
		 this.title = title;
		 this.data = "1000";
	 }

	public static List<Task> findByScene(Long id) {
		Scene scene = Scene.findById(id);
		List<Task> tasks = scene.tasks;
		return tasks;
	}
	

}



