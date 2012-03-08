package controllers;

import java.io.IOException;
import java.util.List;

import models.*;


import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import play.mvc.Controller;
import requests.Comment_request;
import requests.Datum_request;

public class TaskDatum extends Controller{
	
	public static void getVarByTaskAndGroup(){
		Long group_id = params.get("group_id", Long.class);
		Long task_id = params.get("task_id", Long.class);
		TaskData var = TaskData.find("SELECT d  from TaskData d Where d.myGroup.id =? and d.task.id=?"
				, group_id, task_id).first();
		renderTemplate("TaskDatum/var.json", var);
	}
	
	   public static void saveVariables() throws IOException {
	    	String json = IOUtils.toString(request.body);
	    	Datum_request req = new Gson().fromJson(json, Datum_request.class);
	    	//Serialize request
	    	Long project_id = req.project_id;
	    	Long run_id = req.run_id;
	    	Long group_id = req.group_id;
	    	Long task_id = req.task_id;
	    	
	    	MyGroup myGroup = MyGroup.findById(group_id);
	    	Project project = Project.findById(project_id);
	    	Task task = Task.findById(task_id);
	    	TaskData var = myGroup.saveVariableData(project,run_id, task);
	    	renderTemplate("TaskDatum/var.json", var);
	    }
	   
	   public static void updateVariables() throws IOException {
		   String json = IOUtils.toString(request.body);
	    	Datum_request req = new Gson().fromJson(json, Datum_request.class);
	    	//Serialize request
	    	Long data_id = req.data_id;
	    	//Variables to save
		   	String data = req.data;
		   	TaskData var = TaskData.findById(data_id);
		   	var.data = data;
		   	var.save();
		   	renderTemplate("TaskDatum/var.json", var);
		   }
}
