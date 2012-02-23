package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String name;
	public String email;
	public int age;
	public Blob image;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    public List<Act> acts;
}
