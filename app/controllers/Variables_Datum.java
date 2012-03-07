package controllers;

import java.io.IOException;
import java.util.List;

import models.*;


import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import play.mvc.Controller;
import requests.Comment_request;
import requests.Datum_request;

public class Variables_Datum extends Controller{
	
	public static void getVarByTaskAndGroup(){
		Long group_id = params.get("group_id", Long.class);
		Long task_id = params.get("task_id", Long.class);
		Variables_Data var = Variables_Data.find("SELECT v  from Variables_Data v Where v.myGroup.id =? and v.task.id=?"
				, group_id, task_id).first();
		renderTemplate("Variables_Datum/var.json", var);
	}
	
	   public static void saveVariables() throws IOException {
	    	String json = IOUtils.toString(request.body);
	    	Datum_request req = new Gson().fromJson(json, Datum_request.class);
	    	//Serialize request
	    	Long project_id = req.project_id;
	    	Long run_id = req.run_id;
	    	Long group_id = req.group_id;
	    	Long task_id = req.task_id;
	    	//Variables to save
		   	String s1_meter = req.s1_meter;
	    	
	    	MyGroup myGroup = MyGroup.findById(group_id);
	    	Project project = Project.findById(project_id);
	    	Task task = Task.findById(task_id);
	    	Variables_Data var = myGroup.saveVariableData(project,run_id, task, s1_meter);
	    	renderTemplate("Variables_Datum/var.json", var);
	    }
	   
	   public static void updateVariables() throws IOException {
		   String json = IOUtils.toString(request.body);
	    	Datum_request req = new Gson().fromJson(json, Datum_request.class);
	    	//Serialize request
	    	Long var_id = req.var_id;
	    	//Variables to save
		   	String s1_meter = req.s1_meter;
		   	Variables_Data var = Variables_Data.findById(var_id);
		   	var.s1_meter = s1_meter;
		   	var.save();
		   	renderTemplate("Variables_Datum/var.json", var);
		   }
}
