package youtube;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class YoutbueTest extends FunctionalTest{
	
	@Test
	public void postComment() {
        Response response = POST("/http://scihub.uio.no/","application/json","{ \"group_name\": \"group1\", \"task_name\": \"level1\", " +
        		"\"url\": \"http:youtube.com\"}"); 
	    assertIsOk(response);
	}

}
