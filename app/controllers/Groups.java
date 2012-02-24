package controllers;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Before;
import play.mvc.Controller;
import requests.JsonRequest;

public class Groups extends Controller{
    @Before
    static void createRollcallSession() {
    	String contents = "{ \"Session\": {\"username\":\"binden\" , \"password\":\"binden\"} }";
		String url  = "http://imediamac28.uio.no:8080";
		HttpResponse response = WS.url(url).body(contents).post();
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
		String url = "http://imediamac28.uio.no:8080/groups/1.json";//?id=%s";
		//WS.url accept only String type parameters
		JsonElement result = WS.url(url,id).get().getJson();
		renderJSON(result);
	}
	
	//Delete group with ID `1`
	public static void deleteGroup(String id){
		String url = "http://localhost:3000/groups/?id=%s";
		Integer status = WS.url(url,id).delete().getStatus();
		if(status == 1)
			renderText("Group deleted");
	}
	
	//Make a new group with JSON username object "{ \"username\": {\"fahied\", \"jeremy\", \"tony\"}"
	public static void makeGroup(String usernames) throws IOException{
		//Retrive JSON usernames object from client
		String json = IOUtils.toString(request.body);
    	JsonRequest req = new Gson().fromJson(json, JsonRequest.class);
    	
    	String contents = "{ \"username\": {\"fahied\", \"jeremy\", \"tony\"} }";
		String url  = "http://localhost:3000/groups";
		HttpResponse response = WS.url(url).body(contents).post();
		renderText(response);
	}
	
}