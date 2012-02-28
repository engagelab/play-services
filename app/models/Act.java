package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Act extends Model {
	 public String title;
	 
	 @ManyToOne
	 @Required
	 public Project project;
	 
	 @OneToMany(mappedBy="act", cascade=CascadeType.ALL)
	 public List<Scene> scenes;
	 
	 public Act(String title){
		 this.title = title;
		 this.scenes = new ArrayList<Scene>();
	 }

	 
	    public static List<Act>  getAllActs(String title) {
	    	//JPQL query to find all acts
	    	List<Act> acts = Act.findAll();
	    	return acts;
	    }
	    
		public static List<Act> findByProject(Long id) {
			Project project = Project.findById(id);
			List<Act> acts = project.acts;
			return acts;
		}
		
		
		
}
