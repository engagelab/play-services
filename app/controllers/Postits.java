package controllers;

import java.io.IOException;

import java.util.List;

import javax.persistence.Query;

import models.*;


import org.apache.commons.io.IOUtils;

import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Postits extends Controller {

    public static void index() {
        render();
    }
   
   //{user: "jeremyt", sceneId: "simulation1" }
   public static void create() throws IOException {
    	String json = IOUtils.toString(request.body);

    	Postit tmpPostit = new Gson().fromJson(json, Postit.class);
    	Postit p = new Postit(tmpPostit.user, tmpPostit.sceneId);
    	p.save();
    	// Create or Update the scene
//    	Scene scene = Scene.find("select s from Scene s where s.user =?",tmpPostit.sceneId).first();
//    	if (scene != null)
//    		//do nothing
//    		System.out.println("Scene already exist");
//    		
//    	else
//    	{
//    		Scene newScene = new Scene(tmpPostit.user, tmpPostit.sceneId);
//    		newScene.save();
//    	}
    		
    	renderJSON(p);
    }
    /*
     * Show All postit on specific scene and specific user
     * */
    public static void all() {
    	//JPA Function return all postits regardless of user and scene
    	List<Postit> findAll = Postit.findAll();

        //render 404 page if the object does not found
    	notFoundIfNull(findAll);
        renderJSON(findAll);
    }
    
    public static void byUserAndScene() {
    	//if request is parameters
    	String user = params.get("user");
    	String sceneId = params.get("sceneId");
    
    	//JPQL query to find all postits from Postit Table specific to the user and the scene
    	List<Postit> postits = Postit.find("select p from Postit p where p.user =? and p.sceneId=?",user,sceneId).fetch();
    
        //render 404 page if the object does not found
    	notFoundIfNull(postits);
        renderJSON(postits);
    }
    
    public static void update(String id) throws IOException {
    	String json = IOUtils.toString(request.body);
    	Postit tmpPostit = new Gson().fromJson(json, Postit.class);
        Postit foundPostit = Postit.find("select distinct p from Postit p where p._id=?", tmpPostit._id).first() ;
        notFoundIfNull(foundPostit);
    	foundPostit.sceneId = tmpPostit.sceneId;
    	foundPostit.content = tmpPostit.content;
    	foundPostit.user = tmpPostit.user;
    	foundPostit.xpos = tmpPostit.xpos;
    	foundPostit.ypos = tmpPostit.ypos;
    	foundPostit.save();
    	renderJSON(foundPostit);
    }

    /*
     * Delete a Specific Postit using Unique ID
     * */
    public static void delete(String id) {
    	//String uId = params.get("uId");
		Postit.delete("from Postit p where p._id=?", id);
    }

}
