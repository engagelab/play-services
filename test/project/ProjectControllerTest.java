package project;

import org.junit.*;

import groovy.ui.text.FindReplaceUtility;

import java.util.*;

import play.db.jpa.JPABase;
import play.mvc.Http.Response;
import play.test.*;
import models.*;

public class ProjectControllerTest extends FunctionalTest {
	

	@Before
	public void setup() {
	}
	
	@Test
	public void getAllProjects() {
	       Response response = GET("/project");
	       assertIsOk(response);
	}
	
	@Test
	public void getProject() {
	       Response response = GET("/project/1");
	       assertIsOk(response);
	}
	
	@Test
	public void getActsByProject() {
	       Response response = GET("/acts/project/1");
	       assertIsOk(response);
	}
	
	@Test
	public void getScenesByAct() {
	       Response response = GET("/scenes/act/1");
	       assertIsOk(response);
	}
	
	@Test
	public void getTasksByScene() {
	       Response response = GET("/tasks/scene/1");
	       assertIsOk(response);
	}
	
	@Test
	public void getProjectIdByTitle() {
			String title = "aprilrun";
	       Response response = GET("/project/id/"+title);
	       assertIsOk(response);
	}
	
	@Test
	public void getActIdByTitle() {
			String title = "heatpump";
	       Response response = GET("/act/id/"+title);
	       assertIsOk(response);
	}
	
	@Test
	public void getSceneIdByTitle() {
			String title = "simulations";
	       Response response = GET("/scene/id/"+title);
	       assertIsOk(response);
	}
	
	@Test
	public void getTaskIdByTitle() {
			String title = "level1";
	       Response response = GET("/task/id/"+title);
	       assertIsOk(response);
	}
	
	
}
