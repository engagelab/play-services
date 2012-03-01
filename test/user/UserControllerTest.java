package user;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class UserControllerTest extends FunctionalTest{
	
	
	@Test
	public void deleteComment() {
	       Response response = DELETE("/comments/123");
	       assertIsOk(response);
	}
	
	@Test
	public void postComment() {
        Response response = POST("/comments/put/","application/json","{ \"userId\": \"2\", \"projectId\": \"2\", \"taskId\": \"1\", \"title\": \"first\", \"message\": \"this is my first comment\"}"); 
	    assertIsOk(response);
	}

}
