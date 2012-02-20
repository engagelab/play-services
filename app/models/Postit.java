package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Postit extends Model {
	
	//{"scene":"","_id":"4f3e3bd09c95052c17000004","ypos":"","xpos":"","content":"","user":""}
	
	public String sceneId;
	public String ypos;
	public String xpos;
	public String content;
	public String user;
	
	public Postit(String sceneId, String ypos, String xpos, String user) {
		this.sceneId = sceneId;
		this.ypos = ypos;
		this.xpos = xpos;
		this.user = user;
	}
    
}
