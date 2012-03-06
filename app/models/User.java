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
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String name;
	public String email;
	public int age;
	public Blob image;
	
	
	//public List<string> groupIds
	//public string rollcallId
	
	//@ManyToMany(cascade=CascadeType.ALL) 
    //public Set<Project> followedProjects = new HashSet<Project>();; 
	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
//    public List<Comment> comments;
	
	//User Constructor
	public User(String name,String email, int age, Blob image){
		//this.comments = new ArrayList<Comment>();
		this.name = name;
		this.email = email;
		this.age = age;
		this.image = image;
	}

	
//	public User addComment(Project project, Task task, String title,
//			String message) {
//			Comment newComment = new Comment(this, project,task, title, message);
//			this.comments.add(newComment);
//			this.save();
//			return this;
//	}
}
