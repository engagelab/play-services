package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;

public class Scenes extends Controller {
	
    public static void all() {
    	//TODO get user and sceneId parameters
    	//JPQL query to find all Scenes from scene Table specific to the user and the scene
    	List<Scene> scenes = Scene.find("select p from Scene p where p.user =?","fahied").fetch();
    	//JPA Function return all scenes regardless of user and scene
    	//List<Postit> findAll = Postit.findAll();
        //render 404 page if the object does not found
    	notFoundIfNull(scenes);
        renderJSON(scenes);
    }
}
