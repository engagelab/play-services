package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import models.MyGroup;
import models.TPicture;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import play.Play;
import play.mvc.Controller;

public class TPictures extends Controller {
	
	public static String filepath = "./upload/";
	public static String filename = "";

	public static void index() {
		render();
	}

	public static void upload(String qqfile) {
		Long group_id = params.get("grpid", Long.class);
		MyGroup group = MyGroup.findById(group_id);
		if (request.isNew) {
			filename = request.headers.get("x-file-name").value();
			File file;
			try {
				file = new File(filepath +group.name+"_"+ filename);
				InputStream data = request.body;
				OutputStream out = new FileOutputStream(file, true); // appending output stream
				IOUtils.copy(data, out);
				      IOUtils.closeQuietly(data);
				      IOUtils.closeQuietly(out);
			} 
			catch (Exception ex) {
				// catch file exception
				// catch IO Exception later on
				renderJSON("{success: false}");
			}
		}
		String appendedName = group.name + "_"+filename;
		group.addNewPicture(group,appendedName);
		renderJSON("{success: true}");
	}
}
