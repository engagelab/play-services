package youtube;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class YoutbueTest extends FunctionalTest{
	
	@Test
	public void postLink() {
        Response response = POST("/youtube/","application/json","{ \"group_name\": \"group1\", \"task_name\": \"level1\", " +
        		"\"yt_url\": \"http://www.youtube.com/v/CXlXpQcHKCA\"}"); 
	    assertIsOk(response);
	}

	
	@Test
	public void updatePos() {
        Response response = PUT ("/youtube/","application/json","{ \"group_name\": \"group1\", \"task_name\": \"level1\", " +
        		"\"yt_url\": \"http://www.youtube.com/v/CXlXpQcHKCA\"}"); 
	    assertIsOk(response);
	}
}
