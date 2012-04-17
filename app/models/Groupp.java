package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;

import java.util.*;

/**
 * Group entity managed by JPA
 */
@Entity
public class Groupp extends Model {

	public String name;
	public String run_id;
	
	@OneToMany(mappedBy="groupp", cascade=CascadeType.ALL)
    public List<Member> members;
	
	@OneToMany(mappedBy="groupp", cascade=CascadeType.ALL)
    public List<VideoClip> videoclips;
	
	@OneToMany(mappedBy="groupp", cascade=CascadeType.ALL)
    public List<Image> images;
	
	@OneToMany(mappedBy="groupp", cascade=CascadeType.ALL)
    public List<Postit> postits;
	
	@OneToMany(mappedBy="groupp", cascade=CascadeType.ALL)
    public List<TaskData> taskdatum;

	public Groupp() {
		this.postits = new ArrayList<Postit>();
		this.taskdatum = new ArrayList<TaskData>();
		this.videoclips = new ArrayList<VideoClip>();
		this.images = new ArrayList<Image>();
	}

	public Groupp(String name, String run_id) {
		this.postits = new ArrayList<Postit>();
		this.taskdatum = new ArrayList<TaskData>();
		this.videoclips = new ArrayList<VideoClip>();
		this.images = new ArrayList<Image>();
		this.name = name;
		this.run_id = run_id;
	}

	public Postit postComment(Project project, Long run_id, Task task,
			String content, int xpos,int ypos) {
		Postit postit = new Postit(project, run_id, this, task, content,xpos,
				ypos);
		this.postits.add(postit);
		this.save();
		return postit;
	}

	public TaskData updateTaskData(Project project, Long run_id, Task task) {
		TaskData taskdata = new TaskData(project, run_id, this, task);
		this.taskdatum.add(taskdata);
		this.save();
		return taskdata;
	}

	public VideoClip saveVideo(String task_name, String url) {
		Task task = Task.find("byTitle", task_name).first();
		VideoClip vclip = new VideoClip(url, this, task);
		this.videoclips.add(vclip);
		this.save();
		return vclip;
	}

	public Image saveImage(Groupp group, String filename) {
		Image image = new Image(group, filename);
		this.images.add(image);
		this.save();
		return image;
	}
}
