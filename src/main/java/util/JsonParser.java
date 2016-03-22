package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.User;

public class JsonParser {
	
	public User jsonToUser(String json) throws ParseException{
		User user = new User();
		
		JSONObject jsonObj = new JSONObject(json);
		
		user.setId(jsonObj.getInt("id"));
		user.setName(jsonObj.getString("name"));
		user.setGender(jsonObj.getString("gender"));
		
		String dateOfBirthSTR = jsonObj.getString("dateOfBirth");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		user.setDateOfBirth(sdf.parse(dateOfBirthSTR));
		
		return user;
	}
	
	public List<User> listJsonToListUsers (String jsonList) {
		
		List<User> listUsers = new ArrayList<User>();
		
		JSONArray jsonArray = new JSONArray(jsonList);
		
		if (jsonArray.length() > 0){
			jsonArray.forEach(jsonStr -> {
				try {
					User user = this.jsonToUser(jsonStr.toString());
					listUsers.add(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		return listUsers;
	}
	
	public JSONObject userToJson (User user){
		// TODO user to json
		JSONObject jsonObj = new JSONObject(user);
		
		return jsonObj;
	}

}
