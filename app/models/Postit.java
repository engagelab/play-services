package models;

import play.*;
import play.data.validation.*;
import play.db.jpa.*;
import play.libs.Codec;

import javax.persistence.*;
import java.util.*;

@Entity
public class Postit extends Model {
	
	//{"scene":"","_id":"4f3e3bd09c95052c17000004","ypos":"","xpos":"","content":"","user":""}
	@Required
	 public String user;

    @Required
    public String sceneId;
    
//    @ManyToOne
//    @Required
//    public Scene scene;

	@Lob
    @Required
    @MaxSize(10000)
    public String content;
	
	public float xpos;
	public float ypos;
	public Date postedAt;
	public String _id;
	
	public Postit(String user, String sceneId) {
		this.user = user;
		this.sceneId = sceneId;
		this.content = "";
		this.ypos = 0.0f;
		this.xpos = 0.0f;
		this.postedAt = new Date();
		this._id = Codec.UUID();
		}
	
	public Postit(String user, String sceneId,String content, float ypos, float xpos) {
		this.user = user;
		this.sceneId = sceneId;
		this.content = content;
		this.ypos = ypos;
		this.xpos = xpos;
		this.postedAt = new Date();
	}
    
}
