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
	
	public String fbcontent;
	public Date postedAt;
	
	@ManyToOne
    public Comment comment;
	
	public FbComment(Comment comment, String fbcontent) {
		this.comment = comment;
		this.fbcontent = fbcontent;
	}

	@PrePersist
	public void prePersist(){
		postedAt = new Date();
	}

	/**
	 * @return the fbComment
	 */
	public String getFbComment() {
		return fbcontent;
	}

	/**
	 * @param fbComment the fbComment to set
	 */
	public void setFbComment(String fbComment) {
		this.fbcontent = fbComment;
	}
	

}
