package controllers;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import models.Act;
import play.data.validation.Required;
import play.mvc.Controller;

public class Project extends Controller {
	
	@Required
	 public String title;
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    public List<Act> acts;

}
