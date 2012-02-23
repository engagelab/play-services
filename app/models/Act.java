package models;

import java.util.ArrayList;
import java.util.List;

public class Act {
	 public String title;
	 public List<Scene> scenes;
	 
	 public Act(String title){
		 this.title = title;
		 this.scenes = new ArrayList<Scene>();
	 }

}
