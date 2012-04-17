package models;

import java.util.Date;

import javax.persistence.PrePersist;

import play.db.jpa.Model;

public class Run extends Model{

	public String name;
	public Date createdAt;
	
	@PrePersist
	public void prePersist(){
		createdAt = new Date();
	}
}

//TODO need to implement RUN into group
