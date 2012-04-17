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
 * Face-book like Comment entity managed by JPA
 */
@Entity
public class Comment extends Model{
	
	public String content;
	public Date postedAt;
	
	@ManyToOne
    public Postit postit;
	
	@ManyToOne
    public Image image;
	
	@ManyToOne
    public VideoClip vid;

	@PrePersist
	public void prePersist(){
		postedAt = new Date();
	}
	
	public Comment(Postit postit, String content) {
		this.postit = postit;
		this.content = content;
	}

	public Comment(Image image, String content) {
		this.image = image;
		this.content = content;
	}

	public Comment(VideoClip videoClip, String content) {
		this.vid = videoClip;
		this.content = content;
	}
	
	/*
	 * JPA Queries
	 */
	
	public static void deleteCommentsOnPostit(Long id) {
		Postit postit = Postit.findById(id);
		Comment.delete("from Comment c where c.postit=?", postit);
	}
}
