package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import models.*;
public class Projects extends Controller{
	
    public static void getProject(Long id) {
        Project project = Project.findById(id);
        renderTemplate("Projects/project.json", project);
        //We can not use renderJSON() function be it does not support OneToMany Relation maping
        //IllegalStateException occured : circular reference error Offending field: project Offending object: preserveType: false, type: class models.Project, obj: Project[1]
        //renderJSON(project);
    }
    
    public static void getActsByProject(Long id) {
        List <Act> acts = Act.findByProject(id);
        renderTemplate("Acts/list.json", acts);
    }
    
    
    public static void getScenesByAct(Long id) {
        List <Scene> scenes = Scene.findByAct(id);
        renderTemplate("Scenes/list.json", scenes);
    }
    
    public static void getTasksByScene(Long id) {
        List <Task> tasks = Task.findByScene(id);
        renderTemplate("Tasks/list.json", tasks);
    }
    
    public static void getCommentsByTask(Long id) {
        List <Comment> comments = Comment.findByTask(id);
        renderTemplate("Comments/list.json", comments);
    }
    //TODO wrapper function for getPostitbyTask needed.
    
    public static void index(Long id) {
 
    }
    
    public static void postComment(Long taskId) throws IOException {
	    	String json = IOUtils.toString(request.body);
	    	Comment comment = new Gson().fromJson(json, Comment.class);
	    	
            Task task = Task.findById(taskId);
            task.addComment("total","sum of all numbers");
        }
}
