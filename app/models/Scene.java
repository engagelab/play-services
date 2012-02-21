package models;

import java.util.*;
import javax.persistence.*;

import play.data.binding.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Scene extends Model{

	@Required
    public String sceneId;
    
    @Required @As("yyyy-MM-dd")
    public Date postedAt;
    
    @Required

    public String user;
    
    @OneToMany(mappedBy="scene", cascade=CascadeType.ALL)
    public List<Postit> postits;
    
    public Scene(String user, String sceneId) { 
        this.postits = new ArrayList<Postit>();
        //Author and User
        this.user = user;
        this.sceneId = sceneId;
        this.postedAt = new Date();
    }
    
  //Create Empty Postick and return 
    public Postit createPostick(String user) {
        Postit newPostick = new Postit(this.user,this.sceneId);
        this.postits.add(newPostick);
        this.save();
        return newPostick;
    }
    
}