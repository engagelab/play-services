package controllers;

import com.google.gson.JsonElement;

import play.libs.WS;
import play.mvc.Controller;

public class Groups extends Controller{
	
	//Retrieve the list of all groups
	public static void all(){
		//play serve as client to Ruby rollcall server at port XXXX
		JsonElement result = WS.url("http://localhost:3000/groups").get().getJson();
		renderJSON(result);
	}
	
	//Retrieve the group with ID `1`
	public static void findById(String id){		
		//WS.url accept only String type parameters
		JsonElement result = WS.url("http://localhost:3000/groups/?id=%s",id).get().getJson();
		renderJSON(result);
	}
	
	
	//Delete group with ID `1`
	public static void deleteGroup(String id){
		Integer status = WS.url("http://localhost:3000/groups/?id=%s",id).delete().getStatus();
		if(status == 1)
			renderText("Group deleted");
	}
	
	//Make a new group with ID `1`
	public static void makeGroup(String id){
		Integer status = WS.url("http://localhost:3000/groups",id).post().getStatus();
		if(status == 1)
			renderText("Group deleted");
	}
	
}
