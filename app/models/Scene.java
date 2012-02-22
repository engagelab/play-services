package models;

import java.util.*;
import javax.persistence.*;

import play.data.binding.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Scene extends Model{

	@Required
    public String title;
    
    @Required @As("yyyy-MM-dd")
    public Date postedAt;

    public String user;

    public Scene(String user, String title) { 
        this.user = user;
        this.title = title;
        this.postedAt = new Date();
    }
}
