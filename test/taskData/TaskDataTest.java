package taskData;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class TaskDataTest extends FunctionalTest{
	
	
	@Before
	public void setup() {
	}
	
	@Test
	public void taskdata() {
	       Response response = GET("/taskdata/?title=level1&project_id=1&run_id=2&group_id=1");
	       assertIsOk(response);
	}
	
//	@Test
//	public void saveData() {
//        Response response = POST("/taskdata/","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
//        		"\"group_id\": \"1\", \"task_id\": \"1\"}"); 
//	    assertIsOk(response);
//	}
//	
	@Test
	public void updateData() {
        Response response = PUT("/taskdata/","application/json","{ \"data_id\": \"1\", \"data\": \"3000\"}"); 
	    assertIsOk(response);
	}
}
