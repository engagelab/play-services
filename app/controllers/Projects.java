package controllers;

import play.mvc.Controller;
import java.util.List;
import models.*;
public class Projects extends Controller{
	
    public static void getProjet(Long id) {
        Project project = Project.findById(id);
        render(project);
    }
    
    public static void getActsByProject(String title) {
        List <Act> acts = Act.findByProject(title);
        render(acts);
    }
    
    
    public static void getScenesByAct(String title) {
        List <Scene> scenes = Scene.findByAct(title);
        render(scenes);
    }
    
    public static void getTasksByScene(String title) {
        List <Task> tasks = Task.findByScene(title);
        render(tasks);
    }
    
    public static void getCommentsByTask(String title) {
        List <Comment> comments = Comment.findByTask(title);
        render(comments);
    }
    //TODO wrapper function for getPostitbyTask needed.
    
}
