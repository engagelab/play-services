package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Act entity managed by JPA
 */
@Entity
public class Act extends Model {

	public String title;

	@ManyToOne
	@Required
	public Project project;

	@OneToMany(mappedBy = "act", cascade = CascadeType.ALL)
	public List<Scene> scenes;

	public Act(String title) {
		this.title = title;
		this.scenes = new ArrayList<Scene>();
	}

	public static List<Act> fetchActsByProject(Long id) {
		Project project = Project.findById(id);
		List<Act> acts = project.acts;
		return acts;
	}

}
