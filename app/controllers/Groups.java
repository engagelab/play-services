package controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Before;
import play.mvc.Controller;
import requests.Comment_request;
import requests.JsonRequest;
import requests.RunId_request;


public class Groups extends Controller{
    @Before
    static void createRollcallSession() {
    	String contents = "{ \"Session\": {\"username\":\"binden\" , \"password\":\"binden\"} }";
		String url  = "http://imediamac28.uio.no:8080";
		WS.url(url).body(contents).post();
    }
    
	public static void getRunIdByTitle(String title){
		String url = "http://imediamac28.uio.no:8080/groups/runs.json";
		//WS.url accept only String type parameters
		JsonElement result = WS.url(url).get().getJson();
		String res = result.toString();
        if(res == null)
            renderTemplate("/null.json");
        RunId_request req = new Gson().fromJson(res, RunId_request.class);
        req.run_id = "2";
		renderJSON(req);
		
	}
    
	//Retrieve the list of all groups
	public static void all(){
		//play serve as client to Ruby rollcall server at port 8080
		//Talking to Jeremy Ruby Server for testing http://129.240.161.29:4567/groups
		String url = "http://imediamac28.uio.no:8080/groups.json";
		JsonElement result = WS.url(url).get().getJson();
		String res = result.toString();
		renderJSON(res);
	}
	
	//Retrieve the group with ID `1`
	public static void getById(String id){
		//JsonReader.setLenient(true);
		String url = "http://imediamac28.uio.no:8080/groups/" + id + ".json";
		//WS.url accept only String type parameters
		JsonElement result = WS.url(url).get().getJson();
		String res = result.toString();
		renderJSON(res);
	}
	
	//Delete group with ID `1`
	public static void deleteGroup(String id){
		String url = "http://imediamac28.uio.no:8080/groups/" + id;
		Integer status = WS.url(url).delete().getStatus();
		if(status == 1)
			renderText("Group deleted");
	}
	
	//{}
	   public static void postComment() throws IOException {
	    	String json = IOUtils.toString(request.body);
	    	Comment_request req = new Gson().fromJson(json, Comment_request.class);
	    	//Serialize request
	    	Long project_id = req.project_id;
	    	Long run_id = req.run_id;
	    	Long group_id = req.group_id;
	    	Long task_id = req.task_id;
	    	float xpos = req.xpos;
		   	float ypos = req.ypos;
		   	String content = req.content;
	    	
	    	MyGroup myGroup = MyGroup.findById(group_id);
	    	Project project = Project.findById(project_id);
	    	Task task = Task.findById(task_id);
	    	Comment comment = myGroup.postComment(project,run_id, task, content, xpos, ypos);
	    	renderTemplate("Comments/comment.json", comment);
	    }
	   
	   public static void updateComment() throws IOException {
		   	String json = IOUtils.toString(request.body);
		   	Comment_request req = new Gson().fromJson(json, Comment_request.class);
		   	//Serialize request
		   	Long comment_id = req.comment_id;
		   	String content = req.content;
		   	float xpos = req.xpos;
		   	float ypos = req.ypos;
		  
		   	Comment comment = Comment.findById(comment_id);
		   	comment.content = content;
		   	comment.xpos = xpos;
		   	comment.ypos = ypos;
		   	comment.save();
		   	renderTemplate("Comments/comment.json", comment);
		   }
	   
	   public static void deleteComment(Long id){
		   
		   Comment.delete("from Comment c where c.id=?", id);
	   }
	   
	   public static void showAllComments(Long id){
	    	List<Comment> comments = Comment.findAll();
	    	renderTemplate("Comments/list.json", comments);
	   }
	   
	   public static void showCommentbyGT(){
		   Long group_id = params.get("group_id",Long.class);
		   Long task_id = params.get("task_id",Long.class);
			List<Comment> comments = Comment.find("SELECT c  from Comment c Where c.myGroup.id =? and c.task.id=?"
					, group_id, task_id).fetch();
	    	renderTemplate("Comments/list.json", comments);
	   }
	   
	   
}
