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
	public void getCommentsByTask() {
	       Response response = GET("/comments/task/1");
	       assertIsOk(response);
	}
	
	@Test
	public void findByUserAndTask() {
	       Response response = GET("/comments/?userId=2&taskId=1");
	       assertIsOk(response);
	}
	
	@Test
	public void findByUserTaskAndProject() {
	       Response response = GET("/comments/put/?projectId=1&userId=2&taskId=1");
	       assertIsOk(response);
	}
	
	//GET comments by user and project
	//public void findCommentsByUserAndProject
	
	//POST comment by task and user and project (PUT)
	//public void postCommentByPUT
	

	
	//PUT UPDATE comment with new data
	
	
	
}
