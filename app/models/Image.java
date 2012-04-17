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
 * Picture entity managed by JPA
 */
@Entity
public class Image extends Model{

	public String filepath;
	public String filename;
	public Date postedAt;
	public int wxpos;		//store x position of video box on web
	public int wypos;		//store y position of video box on web
	
	@ManyToOne
    public Groupp groupp;
	
	@ManyToOne
    public Task task;
	
	@OneToMany(mappedBy="image", cascade=CascadeType.ALL)
    public List<Comment> comments;
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
		filepath = "/public/upload/"; //TODO later we will store images in JPA blob object
		wxpos = 0;
		wypos = 200; //startup position of you-tube box on web
	}
	
	public Image(Groupp group, String filename) {
		this.groupp = group;
		this.filename =filename;
	}
	
	public Image(Groupp groupp, String filename, int wxpos, int wypos) {
		this.filename = filename;
		this.groupp = groupp;
		this.wxpos = wxpos;
		this.wypos = wypos;
	}

	public Comment postComment(String content) {
		Comment newComment = new Comment(this,content);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
	
	//Queries
	

}
