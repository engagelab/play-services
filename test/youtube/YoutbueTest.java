package youtube;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class YoutbueTest extends FunctionalTest{
	
	@Ignore
	public void postLink() {
		for(int it = 0; it<20;it++)
		{
        Response response = POST("/youtube/","application/json","{ \"group_name\": \"group1\", \"task_name\": \"level1\", " +
        		"\"url\": \"http://www.youtube.com/v/CXlXpQcHKCA\"}"); 
	    assertIsOk(response);
		}
	}

	//{wxpos:50,wypos:25}
	@Test
	public void updatePos() {
        Response response = PUT ("/ytvideosbyid/2","application/json","{ \"yt_id\": \"2\", \"wxpos\": \"20\",  \"wypos\": \"40\"}"); 
	    assertIsOk(response);
	}
	
}
