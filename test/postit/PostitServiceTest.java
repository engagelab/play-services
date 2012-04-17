package postit;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class PostitServiceTest extends FunctionalTest{

	//	GET Services
	@Test
	public void fetchAllPostits() {

	       Response response = GET("/postits");
	       assertIsOk(response);
	}
	
	@Test
	public void fetchPostitsbyG() {

	       Response response = GET("/group/postits?group_id=1");
	       assertIsOk(response);
	}
	
	@Test
	public void fetchPostitsbyGT() {

	       Response response = GET("/group/task/postits?group_id=1&task_id=1");
	       assertIsOk(response);
	}
	
	//	POST Services
	@Ignore
	public void createPostit() {
        Response response = POST("/group/task/postits","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\", \"xpos\": \"2\", \"ypos\": \"2\", \"content\": \"Hello Sir\"}"); 
	    assertIsOk(response);
	}
	
	
	//	PUT Services
	@Ignore
	public void updatePostit() {
        Response response = PUT("/group/task/postits/2","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\",\"comment_id\": \"10\", \"xpos\": \"2\", \"ypos\": \"2\", \"content\": \"Gøt#######ebæeåå'\"}"); 
	    assertIsOk(response);
	}
	
	@Ignore
	public void updatePostitPos() {
        Response response = PUT("/postit/pos/2","application/json","{ \"wxpos\": \"22\", \"wypos\": \"233\"}"); 
	    assertIsOk(response);
	}
	
	//	DELETE Services
	@Ignore
	public void deletePostit() {

	       Response response = DELETE("/group/task/postits/1");
	       assertIsOk(response);
	}
	
}
