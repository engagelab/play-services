package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class Member extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	@ManyToOne
    public User user;
}
