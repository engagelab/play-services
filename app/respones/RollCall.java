package respones;

import models.User;
import java.util.List;

public class RollCall {
	public List<MyGroup> groups;

	public static class MyGroup {
		public Long id;
		public String name;
		public String run_id;
		public List<Member> members;
		// +getters+setters
	}
	
	public static class Member {
		public User user;
		// +getter+setter
	}

}

// public class ResponseData {
// public Response response;
// // +getter+setter
//
// public static class Response {
// public int reference;
// public Data data;
// // +getters+setters
// }
//
// public static class Data {
// public User user;
// // +getter+setter
// }
//
// public static class User {
// public String id;
// public String firstName;
// public String lastName;
// public String email;
// public String phone;
// public Linkedid linkedid;
// // +getters+setters
// }
//
// public static class Linkedid {
// public String id;
// // +getter+setter
// }
// }