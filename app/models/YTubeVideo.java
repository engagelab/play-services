package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import play.db.jpa.Model;

@Entity
public class YTubeVideo extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	
	@OneToMany(mappedBy="vid", cascade=CascadeType.ALL)
    public List<FbComment> fbComments;
	
	@ManyToOne
    public Task task;
	
	public Date postedAt;
	
	public String yt_url;	// youtube url of the video uploaded by student
	public int wxpos;		//x position of youtube box on web
	public int wypos;		//y position of youtube box on web
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
		wxpos = 0;
		//startup position of you-tube box on web
		wypos = 200;
	}
	
	public YTubeVideo(String yt_url, MyGroup myGroup, Task task) {
		this.yt_url = yt_url;
		this.myGroup = myGroup;
		this.task = task;
	}
	
	public YTubeVideo(String yt_url, MyGroup myGroup, Task task, int wxpos, int wypos) {
		this.yt_url = yt_url;
		this.myGroup = myGroup;
		this.task = task;
		this.wxpos = wxpos;
		this.wypos = wypos;
	}

	public FbComment addFbComment(String fbcontent) {
		
		FbComment newfbComment = new FbComment(this,fbcontent);
		this.fbComments.add(newfbComment);
		this.save();
		return newfbComment;
		
	}


}
