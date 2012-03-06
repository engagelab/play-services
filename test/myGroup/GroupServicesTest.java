package myGroup;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class GroupServicesTest extends FunctionalTest{
	
	@Test
	public void postComment() {
        Response response = POST("/comments/","application/json","{ \"project_id\": \"1\", \"run_id\": \"1\", \"group_id\": \"1\", \"task_id\": \"1\"}"); 
	    assertIsOk(response);
	}
	
	@Ignore
	public void updateComment() {
        Response response = PUT("/comments/","application/json","{ \"comment_id\": \"2\" , \"content\": \"Hello Mr\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void deleteComment() {

	       Response response = DELETE("/comments/3");
	       assertIsOk(response);
	}
	
	@Test
	public void showCommentbyGR() {

	       Response response = GET("/comments/");
	       assertIsOk(response);
	}
	

}
