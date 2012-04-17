package video;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class VideoServicesTest extends FunctionalTest {

	//	GET Services
	@Test
	public void fetchVideosByGroup() {
		Response response = GET("/group/videos?group_id=1");
		assertIsOk(response);
	}

	// POST Services
	@Test
	public void storeVideo() {
		Response response = POST("/group/videos", "application/json",
				"{ \"group_name\": \"SORT\", \"task_name\": \"Energioverføring\", "
						+ "\"video_url\": \"http://www.youtube.com/v/CXlXpQcHKCA\"}");
		assertIsOk(response);
	}

	//	PUT Services
	@Test
	public void updateVideoBoxPos() {
		Response response = PUT("/group/videos/1", "application/json",
				"{ \"video_id\": \"1\", \"wxpos\": \"20\",  \"wypos\": \"40\"}");
		assertIsOk(response);
	}
}
