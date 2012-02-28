package project;

import org.junit.*;

import groovy.ui.text.FindReplaceUtility;

import java.util.*;

import play.db.jpa.JPABase;
import play.mvc.Http.Response;
import play.test.*;
import models.*;

public class ProjectControllerTest extends FunctionalTest {
	
	
	@Before
	public void setup() {
	}
	
	@Test
	public void testPost() {
        Response response = POST("/project/act/scene/task/1","application/json","{ \"user\": \"fahied\", \"sceneId\": \"Simmulation 1\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void testGet() {
	       Response response = GET("/project/act/scene/task/comment/");
	       assertIsOk(response);
	}
	
	//{"sceneId"=>nil, "_id"=>"4f4376d69c95054f3a000001", "ypos"=>"304", "xpos"=>"338", "content"=>"yoyo new text", "user"=>nil}
	@Test
	public void testUpdate() {
		//Response response = PUT("/postit/","application/json","{ \"id\": \"1\", \"user\": \"tony\", \"sceneId\": \"4\", \"content\": \"this is an update\", \"ypos\":\"304\", \"xpos\":\"338\" }"); 
		Response response = PUT("/project/act/scene/task/comment/","application/json","{ \"uid\": \"557d8446-fff7-465a-ab8f-c4a740aad4az\", \"content\": \"this is an update\", \"ypos\":\"304\", \"xpos\":\"338\" }");
		assertIsOk(response);
	}
	

}
