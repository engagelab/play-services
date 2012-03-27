/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import play.db.jpa.Model;

/**
 * @author Fahied
 *
 */
@Entity
public class FbComment extends Model{
	
	private String fbComment;
	public Date postedAt;
	
	@ManyToOne
    public Comment comment;
	
	@PrePersist
	public void prePersist(){
		postedAt = new Date();
	}

	/**
	 * @return the fbComment
	 */
	public String getFbComment() {
		return fbComment;
	}

	/**
	 * @param fbComment the fbComment to set
	 */
	public void setFbComment(String fbComment) {
		this.fbComment = fbComment;
	}
	

}
