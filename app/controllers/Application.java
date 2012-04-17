package controllers;

import play.*;
import play.i18n.Lang;
import play.libs.WS;
import play.mvc.*;
import requests.GroupResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;


import models.*;

public class Application extends Controller {

	/**
	 * Index
	 */
//	public static void index() {
//		// play serve as client to Ruby rollcall server at port 8080
//		String url = "http://imediamac28.uio.no:8080/groups.json";
//		JsonElement result = WS.url(url).get().getJson();
//		String res = result.toString();
///*****
// * Dropping parent element from JSON 
// ****/
//		JsonElement json = new JsonParser().parse(res);
//		JsonArray array = json.getAsJsonArray();
//		Iterator iterator = array.iterator();
//
//		List<Groupp> groups = new ArrayList<Groupp>();
//		while (iterator.hasNext()) {
//			JsonElement json2 = (JsonElement) iterator.next();
//			Gson gson = new Gson();
//			GroupResponse gr = gson.fromJson(json2, GroupResponse.class);
//			Groupp newGroup = new Groupp();
//// Check if new group found add to local group list			
//			Groupp check = Groupp.find(
//					"SELECT g  from Groupp g Where g.name=? and g.run_id =? ",
//					gr.group.name, gr.group.run_id).first();
//			if (check == null) {
//				newGroup.name = gr.group.name;
//				newGroup.run_id = gr.group.run_id;
//				newGroup.save();
//			}
//			groups.add(gr.group);
//		}
//		renderJSON(groups);
//	}
//	
//	
//	
//	public static void upload(String qqfile) {
//
//
//		if (request.isNew) {
//
//		    FileOutputStream moveTo = null;
//
//		    Logger.info("Name of the file %s", qqfile);
//		    // Another way I used to grab the name of the file
//		    String filename = request.headers.get("x-file-name").value();
//
//		    Logger.info("Absolute on where to send %s", Play.getFile("").getAbsolutePath() + File.separator + "uploads" + File.separator);
//		    try {
//
//		        InputStream data = request.body;
//
//		        moveTo = new FileOutputStream(new File(Play.getFile("").getAbsolutePath()) + File.separator + "uploads" + File.separator + filename);
//		        IOUtils.copy(data, moveTo);
//
//		    } catch (Exception ex) {
//
//		        // catch file exception
//		        // catch IO Exception later on
//		        renderJSON("{success: false}");
//		    }
//
//		}
//
//
//		renderJSON("{success: true}");
//		} 
}