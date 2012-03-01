package controllers;

import java.util.List;

import models.*;

import play.mvc.Controller;

public class Comments extends Controller {
	
	public static List<Comment> findByUserAndTask() {
		Long userId = params.get("userId",Long.class);
		Long taskId = params.get("taskId",Long.class);

		//JPQL query to Search Comments by User and and task
		List<Comment> comments = Comment.find("SELECT c  from Comment c Where c.user.id =? and c.task.id=?", userId, taskId).fetch();
		renderTemplate("Comments/list.json", comments);
		return comments;
	}
}