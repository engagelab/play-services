package myGroup;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class GroupServicesTest extends FunctionalTest{
	
	@Test
	public void postComment() {
        Response response = POST("/comments/","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\", \"xpos\": \"2\", \"ypos\": \"2\", \"content\": \"Hello Sir\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void updateComment() {
        Response response = PUT("/comments/","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\",\"comment_id\": \"1\", \"xpos\": \"2\", \"ypos\": \"2\", \"content\": \"Welcome Sir\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void deleteComment() {

	       Response response = DELETE("/comments/4");
	       assertIsOk(response);
	}
	
	@Test
	public void showCommentbyGT() {

	       Response response = GET("/comments/?group_id=1&task_id=1");
	       assertIsOk(response);
	}
	

}
