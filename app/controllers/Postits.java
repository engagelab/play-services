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
    	//TODO: parse json object to extract user and scene
    
    	Postit p = new Postit(null, null, null, "tony");
    	p.save();
    	renderJSON(p);
    }
    
    public static void all() {
    	//TODO: need to send user and scene as parameters for the search
    
    	List<Postit> findAll = Postit.findAll();
    	renderJSON(findAll);
    }
    

}
