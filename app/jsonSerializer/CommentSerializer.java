package jsonSerializer;

import java.lang.reflect.Type;

import models.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CommentSerializer implements JsonSerializer<Comment> {

	public JsonElement serialize(Comment t_comment, Type typeofId,
			JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", t_comment.id);
		obj.addProperty("content", t_comment.content);
		obj.addProperty("xpos", t_comment.xpos);
		obj.addProperty("ypos", t_comment.ypos);
		
		JsonArray steps_a = new JsonArray();
		//for(Step stp : t_comment.steps)
		return obj;
	}
}
