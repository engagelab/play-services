package variables_data;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class Variables_Datum_Test extends FunctionalTest{
	
	
	@Before
	public void setup() {
	}
	
	@Test
	public void getVarByTaskAndGroup() {
	       Response response = GET("/var/?group_id=1&task_id=1");
	       assertIsOk(response);
	}
	
	@Test
	public void saveVariables() {
        Response response = POST("/var/","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\", \"s1_meter\": \"2\"}"); 
	    assertIsOk(response);
	}
	
	@Test
	public void updateComment() {
        Response response = PUT("/var/","application/json","{ \"project_id\": \"1\", \"run_id\": \"2\", " +
        		"\"group_id\": \"1\", \"task_id\": \"1\",\"var_id\": \"1\", \"s1_meter\": \"22\"}"); 
	    assertIsOk(response);
	}
}
