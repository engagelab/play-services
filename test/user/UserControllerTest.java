package user;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class UserControllerTest extends FunctionalTest{
	
	
	@Test
	public void deleteComment() {
	       Response response = DELETE("/comments/072f9090-2ce1-4331-8f17-8d55a1913566");
	       assertIsOk(response);
	}
	
	@Test
	public void postComment() {
        Response response = POST("/comments/put/","application/json","{ \"userId\": \"2\", \"projectId\": \"2\", \"taskId\": \"1\", \"title\": \"first\", \"message\": \"this is my first comment\"}"); 
	    assertIsOk(response);
	}
	
	
	@Test
	public void updateComment() {
        Response response = PUT("/comments/put/","application/json","{ \"uid\": \"072f9090-2ce1-4331-8f17-8d55a1913566\", \"title\": \"update\", \"message\": \"this is my first updated comment\"}"); 
	    assertIsOk(response);
	}

}
