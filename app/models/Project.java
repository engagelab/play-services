package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Project extends Model{

	@Required
	String title;
	
	
	//@ManyToMany(mappedBy="followedProjects") 
    //public Set<User> followsByUsers = new HashSet<User>(); 
	
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    public List<Act> acts;
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    public List<Comment> comments;
	
	
	public Project(String title){
		this.acts = new ArrayList<Act>();
		this.title = title;
	}
}
