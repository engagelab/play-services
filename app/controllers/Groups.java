package controllers;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import models.*;


import flexjson.JSONSerializer;

import play.mvc.Before;
import play.mvc.Controller;
import requests.*;
import util.UnicodeString;

/*******************************************************************************
 * Class Name: Group Controller - contain necessary implementations to
 * facilitate the groups activities -
 *******************************************************************************/
public class Groups extends Controller {

	/********************* Render the main page *********************/
	public static void index(String name) {
		List<Groupp> groups = Groupp.findAll();
		render(groups);
	}

	/*
	 *  Group entity operations
	 * /
	/********************* Retrieve the list of all groups *********************/
	public static void fetchAllGroups() {
		List<Groupp> groups = Groupp.findAll();
		// By Using FlexJSON library
		JSONSerializer modelSerializer = new JSONSerializer().include("name",
				"id", "run_id", "members.user.name").exclude("*");
		renderJSON(modelSerializer.serialize(groups));
	}
	/********************* Retrieve the list of all groups by run_id **********/
	public static void fetchGroupsByRunId(Long id) {
		List<Groupp> groups = Groupp.find(
				"SELECT g from Groupp g Where run_id =?", id.toString())
				.fetch();
		// By Using FlexJSON library
		JSONSerializer modelSerializer = new JSONSerializer().include("name",
				"id", "run_id", "members.user.name")
				.exclude("*");
		renderJSON(modelSerializer.serialize(groups));
	}
	
	/********************* Retrieve group by id **********/
	public static void fetchGroupById(Long id) {
		Groupp groupp = Groupp.findById(id);
		// By Using FlexJSON library
		JSONSerializer modelSerializer = new JSONSerializer().include("name",
				"id", "run_id", "members.user.name")
				.exclude("*");
		renderJSON(modelSerializer.serialize(groupp));
	}
	
	/********************* Retrieve all contents by group by id **********/
	public static void fetchGroupContents() {
		Long group_id = params.get("group_id", Long.class);
		Groupp group = Groupp.findById(group_id);
		Groupp tgroup = new Groupp();
		List<VideoClip> vclist = group.videoclips;
		List<Postit> postits = group.postits;

		tgroup.videoclips = vclist;
		tgroup.postits = postits;
		tgroup.id = group.id;
		tgroup.name = group.name;
		tgroup.run_id = group.run_id;

		JSONSerializer modelSerializer = new JSONSerializer().include(
				"videoclips.id", "videoclips.video_url", "postits.xpos",
				"postits.ypos", "postits.wxpos", "postits.wypos",
				"postits.rawcontent", "id", "run_id", "postits.task.id",
				"postits.project.id").exclude("*");
		renderJSON(modelSerializer.serialize(tgroup));
	}
	
	/*
	 *  Postit entity operations
	 * /
	/********************* fetch all Postits **********************************/
	public static void fetchAllPostits() {
		List<Postit> postits = Postit.findAll();
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postits));
	}
	
	/********************* fetch Postits by Group **********************/
	public static void fetchPostitsbyG() {
		Long group_id = params.get("grpid", Long.class);
		List<Postit> postits = Postit.find(
				"SELECT p from Postit p Where groupp_id =?", group_id)
				.fetch();
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postits));
	}

	/********************* fetch Postits by Group and Task **********************/
	public static void fetchPostitsbyGT() {
		Long group_id = params.get("group_id", Long.class);
		Long task_id = params.get("task_id", Long.class);
		List<Postit> postits = Postit
				.find("SELECT p from Postit p Where p.groupp.id =? and p.task.id=?",
						group_id, task_id).fetch();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postits));
	}

	/********************* Create new Postit **********************************/
	public static void createPostit() throws IOException {
		String json = IOUtils.toString(request.body);
		Postit_request req = new Gson().fromJson(json, Postit_request.class);
		// Serialize request
		Long project_id = req.project_id;
		Long run_id = req.run_id;
		Long group_id = req.group_id;
		Long task_id = req.task_id;
		int xpos = req.xpos;
		int ypos = req.ypos;
		String content = req.content;

		Groupp groupp = Groupp.findById(group_id);
		Project project = Project.findById(project_id);
		Task task = Task.findById(task_id);
		Postit postit = groupp.postComment(project, run_id, task, content,
				xpos, ypos);
		// Set postion of Postit notes automatically
		postit.wxpos = (int) ((postit.id * 40) % 800);
		postit.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postit));
	}

	/********************* Update the Postit in FLASH **********************************/
	public static void updatePostit(Long id) throws IOException {
		String json = IOUtils.toString(request.body);
		Postit_request req = new Gson().fromJson(json, Postit_request.class);
		// Unicode conversion
		UnicodeString us = new UnicodeString();
		String content = us.convert(req.content);
		// String content = req.content
		int xpos = req.xpos;
		int ypos = req.ypos;
		Postit postit = Postit.findById(id);
		postit.content = content;
		postit.rawcontent = req.content;
		postit.xpos = xpos;
		postit.ypos = ypos;
		postit.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postit));
	}
	
	/********************* Update the Postit Position on web **********************************/
	public static void updatePostitPos(Long id) throws IOException {
		String json = IOUtils.toString(request.body);
		Postit_request req = new Gson().fromJson(json, Postit_request.class);
		int wxpos = req.wxpos;
		int wypos = req.wypos;
		Postit postit = Postit.findById(id);
		postit.wxpos = wxpos;
		postit.wypos = wypos;
		postit.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"xpos", "ypos", "content", "groupp.id", "project.id",
				"task.id", "rawcontent", "run_id", "wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(postit));
	}
	
	/********************* Delete the Postit **********************************/
	public static void deletePostit(Long id) {
		Comment.deleteCommentsOnPostit(id);
		int itemsDeleted = Postit.delete("from Postit p where p.id=?", id);
		if (itemsDeleted >= 1)
			renderJSON("{success: true}");
		else
			renderJSON("{success: true}");
	}
	
	/*
	 * Comment Entity GET Services
	 * */
	/********************* fetch comments by Postit id ******************/
	public static void fetchCommentsByPostit() {
		Long postit_id = params.get("postit_id", Long.class);
		Postit postit = Postit.findById(postit_id);
		List<Comment> comments = postit.comments;
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comments));
	}
	
	/********************* fetch comments by Picture id ******************/
	public static void fetchCommentsByImage() {
		Long image_id = params.get("image_id", Long.class);
		Image pic = Image.findById(image_id);
		List<Comment> comments = pic.comments;
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comments));
	}
	
	/********************* show fb comments by Video id ******************/
	public static void fetchCommentsByVideo() {
		Long video_id = params.get("video_id", Long.class);
		VideoClip video = VideoClip.findById(video_id);
		List<Comment> comments = video.comments;
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comments));
	}
	
	/*
	 * Comment Entity POST Services
	 * /
	 * 	/********************* add comment on Postit *****************************/
	public static void postCommentOnPostit() throws IOException {
		String json = IOUtils.toString(request.body);
		Comment_request req = new Gson().fromJson(json, Comment_request.class);

		Long postit_id = req.postit_id;
		String content = req.content;
		Postit postit = Postit.findById(postit_id);
		Comment comment = postit.postComment(content);

		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comment));
	}
	/********************* add comment on Picture *****************************/
	public static void postCommentOnImage() throws IOException {
		String json = IOUtils.toString(request.body);
		Comment_request req = new Gson().fromJson(json,Comment_request.class);
		
		Long image_id = req.image_id;
		String content = req.content;
		Image pic = Image.findById(image_id);
		Comment comment = pic.postComment(content);

		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comment));
	}

	/********************* add comment on Youtube Video *****************************/
	public static void postCommentOnVideo() throws IOException {
		String json = IOUtils.toString(request.body);
		Comment_request req = new Gson().fromJson(json, Comment_request.class);
		Long video_id = req.video_id;
		String content = req.content;
		VideoClip video = VideoClip.findById(video_id);
		Comment comment = video.postCommentOnVideo(content);

		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"content").exclude("*");
		renderJSON(modelSerializer.serialize(comment));
	}

	/*
	 * Task Data Entity PUT Services
	 * /
	/********************* Update Task Data **********************************/
	public static void updateTaskData(Long id) throws IOException {
		String json = IOUtils.toString(request.body);
		Datum_request req = new Gson().fromJson(json, Datum_request.class);
		String data = req.data;
		TaskData existing_var = TaskData.findById(id);
		existing_var.taskdata = data;
		existing_var.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"data").exclude("*");
		renderJSON(modelSerializer.serialize(existing_var));
	}

	/*
	 * Youtube Video Entity GET Services
	 * /
	/********************* Fetch Youtube Video by Group id *********************/
	public static void fetchVideosByGroup() {
		Long group_id = params.get("group_id", Long.class);
		Groupp group = Groupp.findById(group_id);
		List<VideoClip> videolist = group.videoclips;
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"video_url", "wxpos", "wypos").exclude("*");
		renderJSON(modelSerializer.serialize(videolist));
	}
	
	/*
	 * Youtube Video Entity PUT Services
	 * /
	/****************** add Youtube links uploaded by a specific Group*********/
	// @parms {group_name:"Test", task_name:"spray can", url:"http://"}
	public static void storeVideo() throws IOException {
		String json = IOUtils.toString(request.body);
		Video_request req = new Gson().fromJson(json, Video_request.class);

		String group_name = req.group_name;
		String task_name = req.task_name;
		String video_url = req.video_url;
		// Remove the base you-tube link
		video_url = video_url.substring(25);
		
		Groupp tgroup = Groupp.find("byName", group_name).first();
		VideoClip video = tgroup.saveVideo(task_name, video_url);
		
		// Set postion of Youtube box automatically
		video.wxpos = (int) ((video.id * 40) % 800);
		video.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"url", "content").exclude("*");
		renderJSON(modelSerializer.serialize(video));
	}

	/********************* Update the YTbox Postion on Web *****************/
	public static void updateVideoBoxPos(Long id) throws IOException {
		String json = IOUtils.toString(request.body);
		Video_request req = new Gson().fromJson(json, Video_request.class);
		int wxpos = req.wxpos;
		int wypos = req.wypos;
		
		VideoClip video = VideoClip.findById(id);
		video.wxpos = wxpos;
		video.wypos = wypos;
		video.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id",
				"wxpos","wypos","video_url", "content").exclude("*");
		renderJSON(modelSerializer.serialize(video));
	}

	/*
	 * Image Entity GET Services
	 * /
	/********************* Get Pictures by Group id **********************************/
	public static void fetchImagesbyG() {
		Long group_id = params.get("grpid", Long.class);
		List<Image> pictures = Image.find(
				"SELECT i from Image i Where i.groupp.id =?", group_id)
				.fetch();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id", "groupp.id",
				 "filename","filepath","wxpos", "wypos").exclude(
				"*");
		renderJSON(modelSerializer.serialize(pictures));
	}
	
	/*
	 * Image Entity PUT Services
	 * /
	/********************* Update the Picturebox  on WEB App**********************************/
	public static void updateImageBoxPos(Long id) throws IOException {
		String json = IOUtils.toString(request.body);
		Video_request req = new Gson().fromJson(json, Video_request.class);
		System.out.println(json);
		int wxpos = req.wxpos;
		int wypos = req.wypos;
		Image pic = Image.findById(id);
		pic.wxpos = wxpos;
		pic.wypos = wypos;
		pic.save();
		
		JSONSerializer modelSerializer = new JSONSerializer().include("id","wxpos", "wypos").exclude("*");
		renderJSON(modelSerializer.serialize(pic));
	}
}



