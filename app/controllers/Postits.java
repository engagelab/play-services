package controllers;

import java.util.List;

import com.google.gson.JsonObject;

import models.Postit;
import play.db.jpa.JPABase;
import play.mvc.*;

public class Postits extends Controller {

    public static void index() {
        render();
    }
    
    public static void create(String json) {
    	Postit p = new Postit(null, null, null, "tony");
    	p.save();
    	renderJSON(p);
    }
    
    public static void all() {
    	List<Postit> findAll = Postit.findAll();
    	renderJSON(findAll);
    }
    

}
