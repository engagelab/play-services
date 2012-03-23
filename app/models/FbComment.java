/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * @author Fahied
 *
 */
@Entity
public class FbComment extends Model{
	
	private String fbComment;
	
	@ManyToOne
    public Comment comment;

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
