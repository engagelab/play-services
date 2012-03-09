package requests;

public class ResponseData {
    
    	 
		public Long task_id;
		public Long metaInfo_id;
        public Long taskdata_id;
        // +getter+setter
         
         public ResponseData(Long task_id, Long metaInfo_id, Long taskdata_id) {
     		this.task_id = task_id;
     		this.metaInfo_id = metaInfo_id;
     		this.taskdata_id = taskdata_id;
     	}
}



//public class ResponseData {
//    public Response response;
//    // +getter+setter
//
//    public static class Response {
//        public int reference;
//        public Data data;
//        // +getters+setters
//    }
//
//    public static class Data {
//        public User user;
//        // +getter+setter
//    }
//
//    public static class User {
//        public String id;
//        public String firstName; 
//        public String lastName;
//        public String email;
//        public String phone;
//        public Linkedid linkedid;
//        // +getters+setters
//    }
//
//    public static class Linkedid {
//        public String id;
//        // +getter+setter
//    }
//}