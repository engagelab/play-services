package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import models.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import play.libs.Codec;
import play.mvc.Controller;

public class Images extends Controller {

	public static void index() {
		render();
	}

	public static void upload(String qqfile) {
		String filename = "";
		
		if (request.isNew) {
			FileOutputStream moveTo = null;
			// used to grab the name of the file
			filename = request.headers.get("x-file-name").value();
			//compose a unique file name
			filename = Codec.UUID() + "."+getFileExtension(filename);
			try {

				InputStream data = request.body;
				moveTo = new FileOutputStream("./public/upload/"+ filename);
				IOUtils.copy(data, moveTo);

			} catch (Exception ex) {

				// catch file exception
				// catch IO Exception later on
				renderJSON("{success: false}");
			}
		}
		Long group_id = params.get("group_id", Long.class);
		Groupp grp = Groupp.findById(group_id);
		grp.saveImage(grp, filename);
		renderJSON("{success: true}");
	}
	
	
	public static String getFileExtension(String filePath){  
		  StringTokenizer stk=new StringTokenizer(filePath,".");  
		  String FileExt="";  
		  while(stk.hasMoreTokens()){  
		   FileExt=stk.nextToken();  
		  }  
		  return FileExt;  
		 } 
}
