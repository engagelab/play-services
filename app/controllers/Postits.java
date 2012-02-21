package controllers;

import java.io.IOException;
import java.util.List;

import models.Postit;

import org.apache.commons.io.IOUtils;

import play.mvc.Controller;

import com.google.gson.Gson;

public class Postits extends Controller {

    public static void index() {
        render();
    }
    
   public static void create() throws IOException {
    	String json = IOUtils.toString(request.body);
    	Postit temp = new Gson().fromJson(json, Postit.class);
    	Postit p = new Postit(temp.sceneId, temp.user,"0", "0" );
    	p.save();
    	renderJSON(p);
    }
    

    
    public static void all() {
    	//TODO: need to send user and scene as parameters for the search
    
    	List<Postit> findAll = Postit.findAll();
    	renderJSON(findAll);
    }
    

}
