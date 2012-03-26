package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class YTubeVideo extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
    public Task task;
	
	public String yt_url;
	
	public YTubeVideo(String yt_url, MyGroup myGroup, Task task) {
		this.yt_url = yt_url;
		this.myGroup = myGroup;
		this.task = task;
	}


}
