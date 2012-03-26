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
    public List<Member> members;
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<YTubeVideo> ytVideos;
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<Comment> comments;
	
	@OneToMany(mappedBy="myGroup", cascade=CascadeType.ALL)
    public List<TaskData> taskdatum;
	
	//User Constructor
	public MyGroup(String name, String run_id){
		this.comments = new ArrayList<Comment>();
		this.taskdatum = new ArrayList<TaskData>();
		this.ytVideos = new ArrayList<YTubeVideo>();
		this.name = name;
		this.run_id = run_id;
	}

	public MyGroup() {
		
	}

	public Comment postComment(Project project, Long run_id, Task task, String content, float xpos, float ypos ) {
		Comment newComment = new Comment(project, run_id, this, task, content, xpos, ypos);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
	
	
	public TaskData createTaskData(Project project, Long run_id, Task task) {
		TaskData taskdata = new TaskData(project, run_id, this,task);
		this.taskdatum.add(taskdata);
		this.save();
		return taskdata;
	}

	public YTubeVideo addYTlink(String task_name, String url) {
		Task task = Task.find("byTitle", task_name).first();
		YTubeVideo nLink = new YTubeVideo(url, this,task);
		this.ytVideos.add(nLink);
		this.save();
		return nLink;
	}
}
