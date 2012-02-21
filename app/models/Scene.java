package models;

import java.util.*;
import javax.persistence.*;

import play.data.binding.*;
import play.data.validation.*;
import play.db.jpa.Model;

public class Scene {

	@Required
    public String title;
    
    @Required @As("yyyy-MM-dd")
    public Date postedAt;
    
    @Required
    //TODO will it be ManyToOne relationship?? or OneToOne?
    public User user;
    
    @OneToMany(mappedBy="scene", cascade=CascadeType.ALL)
    public List<Postit> postits;
    
    public Scene(User user, String title) { 
        this.postits = new ArrayList<Postit>();
        //Author and User
        this.user = user;
        this.title = title;
        this.postedAt = new Date();
    }
    
}
