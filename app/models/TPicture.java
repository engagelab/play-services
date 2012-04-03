package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import play.db.jpa.Model;

@Entity
public class TPicture extends Model{

	public String filepath;
	public String filename;
	
	@ManyToOne
    public MyGroup myGroup;
	
	@ManyToOne
    public Task task;
	
	public Date postedAt;

	public int wxpos;		//x position of youtube box on web
	public int wypos;		//y position of youtube box on web
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
		filepath = "./upload/";
		wxpos = 0;
		//startup position of you-tube box on web
		wypos = 200;
	}
	
	public TPicture(MyGroup group, String filename) {
		this.myGroup = group;
		this.filename =filename ;
	}
	
	public TPicture(MyGroup myGroup, String filename, int wxpos, int wypos) {
		this.filename = filename;
		this.myGroup = myGroup;
		this.wxpos = wxpos;
		this.wypos = wypos;
	}
	
	//Queries
	

}
