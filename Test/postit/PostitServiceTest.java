package postit;

import org.junit.*;

import groovy.ui.text.FindReplaceUtility;

import java.util.*;

import play.db.jpa.JPABase;
import play.mvc.Http.Response;
import play.test.*;
import models.*;

public class PostitServiceTest extends FunctionalTest {

	
	
	@Before
	public void setup() {
	}
	
	@Test
	public void testPost() {
		   Response response = POST("/postit/","application/json","{ author: 'tony' }"); 
	       assertIsOk(response);
	}
	 
	
	@Test
	public void testGet() {
	       Response response = GET("/postit/");
	       assertIsOk(response);
	}

}
