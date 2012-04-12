package fbcomment;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class FBCommentTest extends FunctionalTest{
	
	@Test
	public void postComment() {
        Response response = POST("/fbcomments","application/json","{ \"comment_id\": \"1\", \"fbcontent\": \"this is from post request\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void postCommentOnPic() {
        Response response = POST("/fbonpic","application/json","{ \"pic_id\": \"1\", \"fbcontent\": \"this is from post request\"}"); 
	    assertIsOk(response);
	}
	
	
	@Test
	public void postCommentOnVid() {
        Response response = POST("/fbonvid","application/json","{ \"vid_id\": \"1\", \"fbcontent\": \"this is from post request\"}"); 
	    assertIsOk(response);
	}
}
