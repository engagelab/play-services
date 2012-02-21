package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Postit extends Model {
	
	//{"scene":"","_id":"4f3e3bd09c95052c17000004","ypos":"","xpos":"","content":"","user":""}
	public String user;
	public String sceneId;
	public String content;
	public float xpos;
	public float ypos;
	
	public Postit(String user, String sceneId) {
		this.user = user;
		this.sceneId = sceneId;
		this.content = "";
		this.ypos = 0.0f;
		this.xpos = 0.0f;
	}
	
	public Postit(String user, String sceneId,String content, float ypos, float xpos) {
		this.user = user;
		this.sceneId = sceneId;
		this.content = content;
		this.ypos = ypos;
		this.xpos = xpos;
	}
    
}
