package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import play.db.jpa.Model;

@Entity
public class YTubeVideo extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
    public Task task;
	
	public String yt_url;	// youtube url of the video uploaded by student
	public int wxpos;		//x position of youtube box on web
	public int wypos;		//y position of youtube box on web
	
	@PrePersist
	public void prePersist(){
		wxpos = 0;
		wypos = 0;
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


}
