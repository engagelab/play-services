package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import play.db.jpa.Model;
import util.UnicodeString;

@Entity
public class TPicture extends Model{

	public String filepath;
	public String filename;
	
	@ManyToOne
    public MyGroup myGroup;
	
	@OneToMany(mappedBy="pic", cascade=CascadeType.ALL)
    public List<FbComment> fbComments;
	
	@ManyToOne
    public Task task;
	
	public Date postedAt;

	public int wxpos;		//x position of youtube box on web
	public int wypos;		//y position of youtube box on web
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
		filepath = "/public/upload/";
		wxpos = 0;
		//startup position of you-tube box on web
		wypos = 200;
	}
	
	public TPicture(MyGroup group, String filename) {
		this.myGroup = group;
		this.filename =filename;
	}
	
	public TPicture(MyGroup myGroup, String filename, int wxpos, int wypos) {
		this.filename = filename;
		this.myGroup = myGroup;
		this.wxpos = wxpos;
		this.wypos = wypos;
	}

	public FbComment addFbComment(String fbcontent) {
		FbComment newfbComment = new FbComment(this,fbcontent);
		this.fbComments.add(newfbComment);
		this.save();
		return newfbComment;
	}
	
	//Queries
	

}
