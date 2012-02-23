package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Task {
	 public String title;
	 public List<Comment> comments;
	 public List<Postit> postits;
	 
	 public Task(String title){
		 this.title = title;
		 this.comments = new ArrayList<Comment>();
		 this.postits = new ArrayList<Postit>();
	 }
}
