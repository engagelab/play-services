//you can use private members with getter and setter

package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import play.libs.Codec;
import play.mvc.Controller;
import util.UnicodeString;

/**
 * Postit entity managed by JPA
 */
@Entity
public class Postit extends Model {

	@OneToMany(mappedBy = "postit", cascade = CascadeType.ALL)
	public List<Comment> comments;

	@ManyToOne
	@Required
	public Groupp groupp;

	@ManyToOne
	@Required
	public Project project;

	@ManyToOne
	@Required
	public Task task;

	public Long run_id;

	// Variables to store xy position of Postit on Flash App
	public int xpos;
	public int ypos;

	// Variables to store xy position of Postit on Web App
	public int wxpos;
	public int wypos;

	@Lob
	public String content;

	// variable to store content data in Unicode format for Flash App
	@Lob
	public String rawcontent;

	public Date postedAt;

	// preset values for the concerned varialbes
	@PrePersist
	public void prePersist() {
		postedAt = new Date();
		wxpos = 0;
		wypos = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param project	Name of Project i.e. Miracle
	 * @param run_id	Number of project run
	 * @param groupp	Group made this postit note
	 * @param task		Task on which postit note made
	 * @param content	String content of the postit
	 * @param xpos		x-Position of postit in flash app
	 * @param task		y-Position of postit in flash app
	 */
	public Postit(Project project, Long run_id, Groupp groupp, Task task,
			String content, int xpos, int ypos) {
		this.project = project;
		this.run_id = run_id;
		this.groupp = groupp;
		this.task = task;
		this.content = content;
		this.rawcontent = toUnicode(content);
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	/*
	 * JPA Queries
	 */

	/**
	 * Find a all postit notes by task id.
	 */
	
	public static List<Postit> findByGroup(Long id) {
		Groupp groupp = Groupp.findById(id);
		List<Postit> postits = groupp.postits;
		return postits;
	}

	public static void deletePostit(Long id) {
		Postit.delete("from Comment c where c.id=?", id);
	}

	public static List<Postit> findByTask(Long id) {
		Task task = Task.findById(id);
		List<Postit> postits = task.postits;
		return postits;
	}
	
	//TODO need to implemented
	public static Postit updatePostit(Long id, int xpos, int ypos, String content) {
		Postit postit = Postit.findById(id);
		return postit;
	}
	
	/*
	 * Operations on other Models
	 */
	
	/**
	 * Add a comment on Postit
	 */
	
	private String toUnicode(String content) {
		UnicodeString us = new UnicodeString();
		String ucontent = us.convert(content);
		return ucontent;
	}
	
	public Comment postComment(String content) {
		Comment newComment = new Comment(this, content);
		this.comments.add(newComment);
		this.save();
		return newComment;
	}
}
