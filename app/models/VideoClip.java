package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import play.db.jpa.Model;
/**
 * You-tube Videos entity managed by JPA
 */
@Entity
public class VideoClip extends Model {
	
public Date postedAt;
	
	public String video_url;	// video url of the video uploaded by student
	public int wxpos;			//x position of video box on web
	public int wypos;			//y position of video box on web
	
	@ManyToOne
    public Groupp groupp;
	
	@OneToMany(mappedBy="vid", cascade=CascadeType.ALL)
    public List<Comment> comments;
	
	@ManyToOne
    public Task task;
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
		wxpos = 0;
		//startup position of you-tube box on web
		wypos = 200;
	}
	
	public VideoClip(String video_url, Groupp groupp, Task task) {
		
		this.video_url = video_url;
		this.groupp = groupp;
		this.task = task;
	}
	
	public VideoClip(String video_url, Groupp groupp, Task task, int wxpos, int wypos) {
		
		this.video_url = video_url;
		this.groupp = groupp;
		this.task = task;
		this.wxpos = wxpos;
		this.wypos = wypos;
	}

	public Comment postCommentOnVideo(String content) {
		
		Comment newComment = new Comment(this,content);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
}
