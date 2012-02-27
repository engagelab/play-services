package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Project extends Model{

	@Required
	String title;
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    public List<Act> acts;
}
