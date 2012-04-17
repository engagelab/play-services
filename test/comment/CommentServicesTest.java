package comment;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class CommentServicesTest extends FunctionalTest{
	
	//	GET Services
	@Test
	public void fetchCommentsByPostit() {

	       Response response = GET("/postit/comments?postit_id=2");
	       assertIsOk(response);
	}
	
	@Test
	public void fetchCommentsByImage() {

	       Response response = GET("/image/comments?image_id=1");
	       assertIsOk(response);
	}
	
	@Test
	public void fetchCommentsByVideo() {

	       Response response = GET("/video/comments?video_id=1");
	       assertIsOk(response);
	}
	
	//	POST Services
	@Test
	public void postCommentonPostit() {
        Response response = POST("/postit/comments","application/json","{ \"postit_id\": \"2\", \"content\": \"on Postit\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void postCommentOnImage() {
        Response response = POST("/image/comments","application/json","{ \"image_id\": \"1\", \"content\": \"on Image\"}"); 
	    assertIsOk(response);
	}
	
	
	@Test
	public void postCommentOnVideo() {
        Response response = POST("/video/comments","application/json","{ \"video_id\": \"1\", \"content\": \"on Video\"}"); 
	    assertIsOk(response);
	}
}
