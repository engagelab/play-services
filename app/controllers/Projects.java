package controllers;

import play.data.validation.Required;
import play.mvc.Controller;
import requests.ResponseData;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import models.*;

/*******************************************************************************
 *	Class Name: Project Controller
 * - contain necessary implementations to facilitate retrieve data-set to a specific project. 
 * - it also initialize some of the variables relating to a specific task.
 *******************************************************************************/

public class Projects extends Controller{
	
    public static void getAllProjects() {
        List <Project> projects = Project.findAll();
        renderTemplate("Projects/list.json", projects);
    }
    
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
    
    /////////////////////////////////////////////////////////////////////////
    
    public static void getProjectIdByTitle(String title) {
    	//String title = params.get("title");
        Project project = Project.find("byTitle",title).first();
        if(project == null)
        renderTemplate("/null.json");
        renderTemplate("Projects/project.json", project);
    }
    
    public static void getActIdByTitle(String title) {
        Act act = Act.find("byTitle",title).first();
        if(act == null)
            renderTemplate("/null.json");
            renderTemplate("Acts/act.json", act);
    }
    
    public static void getSceneIdByTitle(String title) {
        Scene scene = Scene.find("byTitle",title).first();
        if(scene == null)
            renderTemplate("/null.json");
            renderTemplate("Scenes/scene.json", scene);
    }
    
    public static void getTaskIdByTitle() {
    	String title = params.get("title");
    	Long project_id = params.get("project_id", Long.class);
    	Long run_id = params.get("run_id", Long.class);
    	Long myGroup_id = params.get("group_id", Long.class);
    	Task task = Task.find("byTitle",title).first();
    	Long task_id = task.id;
    	
    	//find or create Taskdata object as group goes to specific task
    	TaskData taskdata = TaskData.find("SELECT td  from TaskData td Where td.project.id=? and td.myGroup.id =? and td.task.id=? "
				,project_id, myGroup_id, task_id).first();
    	if(taskdata == null)
    	{
    		MyGroup group = MyGroup.findById(myGroup_id);
    		Project project = Project.findById(project_id);
    		taskdata = group.createTaskData(project, run_id, task);
    		taskdata.save();
    	}
    	renderTemplate("TaskDatum/taskdata.json", taskdata, task_id);
    }


    //TODO wrapper function for getPostitbyTask needed.
    
    public static void index(Long id) {
 
    }
}
