package controllers;

import java.util.List;

import models.*;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Scenes extends Controller {
	
    public static void all() {
    	List<Scene> findAll = Scene.findAll();
    	//render 404 page if the object does not found
    	//notFoundIfNull(scenes);
        renderJSON(findAll);
    }
    
    //GET ALL scenes by a specific user
    public static void byUser(String userId) {
    	//JPQL query to find all Scenes from scene Table specific to the user and the scene
    	//List<Scene> scenes = Scene.find("select p from Scene p where p.user =?",userId).fetch();
    	//renderJSON(scenes);
    }
}
