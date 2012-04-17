package controllers;

import java.util.List;

import models.*;

import play.mvc.Controller;

public class Postits extends Controller {
	
	public static List<Postit> findByUserAndTask() {
		Long userId = params.get("userId",Long.class);
		Long taskId = params.get("taskId",Long.class);

		//JPQL query to Search Postits by User and and task
		List<Postit> postits = Postit.find("SELECT c  from Comment c Where c.user.id =? and c.task.id=?", userId, taskId).fetch();
		renderTemplate("Postits/list.json", postits);
		return postits;
	}
	
	public static List<Postit> findByUserTaskAndProject() {
		Long userId 	= params.get("userId",Long.class);
		Long taskId 	= params.get("taskId",Long.class);
		Long projectId 	= params.get("projectId",Long.class);

		//JPQL query to Search Postits by User and and task
		List<Postit> postits = Postit.find("SELECT c  from Comment c Where c.project.id=? and c.user.id =? and c.task.id=?", projectId,userId, taskId).fetch();
		renderTemplate("Postits/list.json", postits);
		return postits;
	}
	
}
