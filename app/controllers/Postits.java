package controllers;

import java.io.IOException;
import java.util.List;

import models.Postit;

import org.apache.commons.io.IOUtils;

import play.db.jpa.JPABase;
import play.mvc.Controller;

import com.google.gson.Gson;

public class Postits extends Controller {

    public static void index() {
        render();
    }
   
   //{user: "jeremyt", sceneId: "simulation1" }
   public static void create() throws IOException {
    	String json = IOUtils.toString(request.body);
    	Postit tmpPostit = new Gson().fromJson(json, Postit.class);
    	Postit p = new Postit(tmpPostit.sceneId, tmpPostit.user,"0", "0" );
    	p.save();
    	renderJSON(p);
    }
    

    
    public static void all() {
    	//TODO: need to send user and scene as parameters for the search
    	List<Postit> findAll = Postit.findAll();
    	renderJSON(findAll);
    }
    
    
    public static void update() throws IOException {
    	String json = IOUtils.toString(request.body);
    	Postit tmpPostit = new Gson().fromJson(json, Postit.class);
    	Postit foundPostit = Postit.findById(tmpPostit.id);
    	
    	//Postick newPostick = Postick.find("select distinct p from Postick p where p.randomId=?", randomId).first() ;
    	
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
    public static void delete() {
    	String uId = params.get("uId");
		Postit.delete("from Postit p where p.uId=?", uId);
    }

}
