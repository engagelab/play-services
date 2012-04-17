package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;


/**
 * User entity managed by JPA
 */
@Entity
public class User extends Model {
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    public List<Member> members;

	public String name;
	public String email;
	public int age;
	public Blob image;
	
	
	//User Constructor
	public User(String name,String email, int age, Blob image){
		this.name = name;
		this.email = email;
		this.age = age;
		this.image = image;
	}

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
