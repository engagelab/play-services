package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class YTubeVideo extends Model {
	
	@ManyToOne
    public MyGroup myGroup;
	
	
	public String url;
	
	
	
	
	
	
	

}
