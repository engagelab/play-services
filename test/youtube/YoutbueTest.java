package youtube;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class YoutbueTest extends FunctionalTest{
	
	@Test
	public void postLink() {
        Response response = POST("/youtube/","application/json","{ \"group_name\": \"group1\", \"task_name\": \"level1\", " +
        		"\"url\": \"http:youtube.com\"}"); 
	    assertIsOk(response);
	}

}
