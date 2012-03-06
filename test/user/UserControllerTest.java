package user;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class UserControllerTest extends FunctionalTest{
	
	@Test
	public void postComment() {
        Response response = POST("/comments/","application/json","{ \"userId\": \"2\", \"projectId\": \"2\", \"taskId\": \"1\", \"title\": \"first\", \"message\": \"this is my first comment\"}"); 
	    assertIsOk(response);
	}
	
	@Ignore
	public void updateComment() {
        Response response = PUT("/comments/put/","application/json","{ \"id\": \"4\", \"title\": \"update\", \"message\": \"this is my first updated comment\"}"); 
	    assertIsOk(response);
	}
	
	@Ignore
	public void deleteComment() {
	       Response response = DELETE("/comments/6");
	       assertIsOk(response);
	}

}
