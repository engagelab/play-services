package models;


//This class represent the generic model for request that can be parsed to any other Model 
public class JsonRequest {

	//{ \"userid\": \"1\", \"taskid\": \"1\", \"title\": \"Simmulation 1\",\"message\": \"Simmulation 1\,\"uid\": \"123\"}
	
	public Long projectId;
	public Long userId;
	public Long taskId;
	public String uid;
	public String title;
	public String message;
}
