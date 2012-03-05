package controllers;

import play.*;
import play.mvc.*;
import requests.JsonRequest;

import java.io.IOException;
import java.util.*;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.*;

/**
 * <p>User controller which supports basic HTTP operations like {@link #show(Long) GET}, {@link #persist(User) POST},
 * {@link #update(Long) PUT} and {@link #delete(Long) DELETE}.</p>
 * 
 * <p>Additionally it shows an index page which lists all known {@link User users}.</p>
 */
public class Users extends Controller {

	/**
	 * Shows a list of all users in the DB.
	 */
    public static void index() {
    	final List<User> users = User.all().fetch();
        
    	render(users);
    }
    
    /**
     * Persists a new user. 
     * 
     * @param user	The user to persist.
     */
    public static void persist(User user) {
    	// Persist the user
        user.save();
        
        // Show the user
        show(user.id);
    }

    /**
     * Updates a single user.
     * 
     * @param id	The ID of the user to update.
     */
    public static void update(Long id) {
    	// Fetch from user from DB
        User user = safeFindById(id);
        
        // Set new values
        user.edit("user", params.all());

        // Persist user
        user.save();
        
        // Show updated user
        show(id);
    }

    /**
     * Deletes a single user.
     * 
     * @param id	The ID of the user to delete.
     */
    public static void delete(Long id) {
    	// Fetch user from DB
    	User deletable = safeFindById(id);
        
    	// Delete user
        deletable.delete();
        
        // Show index page
        index();
    }

    /**
     * Shows a single user.
     * 
     * @param id	The ID of the user to show.
     */
    public static void show(Long id)  {
    	// Fetch user from DB
        User user = safeFindById(id);
        
        // Show user
        render(user);
    }
    
    /**
     * Offers the image attachment of a single user to download.
     * 
     * @param id	The ID of the user whose image should be shown.
     */
    public static void image(Long id) {
    	// Fetch user from DB
        User user = safeFindById(id);

        // Check image availability
        if (user.image != null && user.image.exists()) {
        	// Send image
        	response.contentType = user.image.type();
        	renderBinary(user.image.get(), user.image.length());
        } else {
        	// Send 404
        	notFound();
        }
    }
    
    /**
     * Tries to fetch an {@link User} from the database. If the user could not be found
     * a 404 error will be thrown.
     * 
     * @param id	The ID of the user to fetch.
     * @return		The user with the given ID or a 404.
     */
    private static User safeFindById(Long id) {
    	User user = User.findById(id);
    	notFoundIfNull(user);
    	return user;
    }
    
	public static void postComment() throws IOException {
		String json = IOUtils.toString(request.body);
    	JsonRequest req = new Gson().fromJson(json, JsonRequest.class);
    	
    	//tmpComment.get("userid")
    	//get the project id from json look up the project
    	Long projectId = req.projectId.longValue();
    	Project project = Project.findById(projectId);
    
    	//get the userid from the json lookup the user
    	Long userId 	= req.userId.longValue();
    	User user 		= User.findById(userId);
    	
      	//get the taskid from the json and lookup the task
    	Long taskId 	= req.taskId.longValue();
    	Task task 		= Task.findById(taskId);
    	
    	String title 	= req.title.toString();
    	String message 	= req.message.toString();
    	
    	user.addComment(project, task, title, message);
	}
	
	
	public static void deleteComment(Long id) {
		
    	Comment.deleteComment(id);
	}
	
	
	public static void updateComment() throws IOException {
		
		String json = IOUtils.toString(request.body);
    	JsonRequest req = new Gson().fromJson(json, JsonRequest.class);
    	
    	Long id 		= req.id.longValue();
    	String title 	= req.title.toString();
    	String message 	= req.message.toString();
    	Comment.updateComment(id, title, message);
	}
	
	public static void createGroup() {
	
		//message json - list of usernames
		//check if the usernames existing play, if not create new user
		//rest call to rollcall create group with usernames
		//get the groupId just created
		//render groupId to jeremy via json
	}
}