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
import requests.Postit_request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Postits extends Controller {

    public static void index() {
        render();
    }
   
   //{project_id: "1", group_id: "1" , task_id: "1"}
   public static void create() throws IOException {
    	String json = IOUtils.toString(request.body);
    	Postit_request req = new Gson().fromJson(json, Postit_request.class);
    	//Serialize request
    	Long group_id = req.group_id;
    	Long project_id = req.project_id;
    	Long task_id = req.task_id;
    	
    	MyGroup myGroup = MyGroup.findById(group_id);
    	Project project = Project.findById(project_id);
    	Task task = Task.findById(task_id);
    	Postit newPostit = myGroup.createPostit(project, task);
    	renderTemplate("Postits/postit.json", newPostit);
    }
   
   public static void update() throws IOException {
   	String json = IOUtils.toString(request.body);
   	Postit_request req = new Gson().fromJson(json, Postit_request.class);
   	//Serialize request
   	Long group_id = req.group_id;
   	Long postit_id = req.postit_id;

   	float xpos = req.xpos;
   	float ypos = req.ypos;
   	String content = req.content;
   	
   	MyGroup myGroup = MyGroup.findById(group_id);
 
   	myGroup.updatePostit(postit_id, xpos, ypos, content);
   	renderJSON(myGroup);
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
    
//    public static void byUserAndScene() {
//    	//if request is parameters
//    	String user = params.get("user");
//    	String sceneId = params.get("sceneId");
//    
//    	//JPQL query to find all postits from Postit Table specific to the user and the scene
//    	List<Postit> postits = Postit.find("select p from Postit p where p.user =? and p.sceneId=?",user,sceneId).fetch();
//    
//        //render 404 page if the object does not found
//    	notFoundIfNull(postits);
//        renderJSON(postits);
//    }
    
//    public static void update(String id) throws IOException {
//    	String json = IOUtils.toString(request.body);
//    	Postit tmpPostit = new Gson().fromJson(json, Postit.class);
//        Postit foundPostit = Postit.find("select distinct p from Postit p where p._id=?", tmpPostit._id).first() ;
//        notFoundIfNull(foundPostit);
//    	
//    	foundPostit.content = tmpPostit.content;
//    
//    	foundPostit.xpos = tmpPostit.xpos;
//    	foundPostit.ypos = tmpPostit.ypos;
//    	foundPostit.save();
//    	renderJSON(foundPostit);
//    }

    /*
     * Delete a Specific Postit using Unique ID
     * */
//    public static void delete(String id) {
//    	//String uId = params.get("uId");
//		Postit.delete("from Postit p where p._id=?", id);
//    }

}
