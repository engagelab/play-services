package models;

import java.util.*;

import javax.persistence.*;

import play.data.binding.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Scene extends Model{

	@Required
    public String title;
	
	 @ManyToOne
	 @Required
	 public Act act;
	 
	 @OneToMany(mappedBy="scene", cascade=CascadeType.ALL)
	 public List<Task> tasks;

    public Scene(String title) { 
    	this.tasks = new ArrayList<Task>();
        this.title = title;
    }

	public static List<Scene> findByAct(Long id) {
		Act act = Act.findById(id);
		List<Scene> scenes = act.scenes;
		return scenes;
	}

}
