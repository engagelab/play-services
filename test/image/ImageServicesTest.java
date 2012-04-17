package image;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ImageServicesTest extends FunctionalTest{
	
//	GET Services
	
	@Test
	public void fetchImagesByG() {
	       Response response = GET("/group/images?group_id=1");
	       assertIsOk(response);
	}
	
	//	POST Services
	
	//	PUT Services
	@Test
	public void updatePicBoxPos() {
        Response response = PUT("/group/images/2","application/json","{  \"wxpos\": \"2\", \"wypos\": \"2\"}"); 
	    assertIsOk(response);
	}
}
