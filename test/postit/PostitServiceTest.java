package postit;

import org.junit.*;

import groovy.ui.text.FindReplaceUtility;

import java.util.*;

import play.db.jpa.JPABase;
import play.mvc.Http.Response;
import play.test.*;
import models.*;

public class PostitServiceTest extends FunctionalTest {

	
	
	@Before
	public void setup() {
	}
	
	@Test
	public void create() {
        Response response = POST("/postit/","application/json","{ \"project_id\": \"1\", \"group_id\": \"1\" , \"task_id\": \"1\"}"); 
	    assertIsOk(response);
	}
	
	@Ignore
	public void testGet() {
	       Response response = GET("/postit/");
	       assertIsOk(response);
	}
	
	//{"sceneId"=>nil, "_id"=>"4f4376d69c95054f3a000001", "ypos"=>"304", "xpos"=>"338", "content"=>"yoyo new text", "user"=>nil}
	@Ignore
	public void testUpdate() {
		//Response response = PUT("/postit/","application/json","{ \"id\": \"1\", \"user\": \"tony\", \"sceneId\": \"4\", \"content\": \"this is an update\", \"ypos\":\"304\", \"xpos\":\"338\" }"); 
		Response response = PUT("/postit/","application/json","{ \"uid\": \"557d8446-fff7-465a-ab8f-c4a740aad4az\", \"content\": \"this is an update\", \"ypos\":\"304\", \"xpos\":\"338\" }");
		assertIsOk(response);
	}
	
}
