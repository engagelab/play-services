package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 * Membership entity managed by JPA
 */
@Entity
public class Member extends Model {
	
	@ManyToOne
    public Groupp groupp;
	@ManyToOne
    public User user;
}
