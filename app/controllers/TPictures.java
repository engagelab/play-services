package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import models.TPicture;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import play.Play;
import play.mvc.Controller;

public class TPictures extends Controller {
	
	public static String FILE_PATH = "./upload/";
	public static String filename = "";

	public static void index() {
		render();
	}

	public static void upload(String qqfile) {
		Long group_id = params.get("grpid", Long.class);
		Long task_id = params.get("task_id", Long.class);
		if (request.isNew) {
			filename = request.headers.get("x-file-name").value();
			File file;
			try {
				file = new File(FILE_PATH + filename);
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
//		TPicture newPic = new TPicture(FILE_PATH,filename);
		renderJSON("{success: true}");
	}
}
