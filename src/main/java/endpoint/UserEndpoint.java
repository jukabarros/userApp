package endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.JsonParser;

public class UserEndpoint implements Serializable{

	private static final long serialVersionUID = -8578600928021044681L;
	
	private List<User> listUsers;
	
	private User user;
	
	private AbstractRequest absRequest;
	
	private JsonParser jsonParser;
	
	public UserEndpoint() {
		super();
		this.listUsers = new ArrayList<User>();
		this.user = new User();
		this.absRequest = new AbstractRequest();
		this.jsonParser = new JsonParser();
	}


	// GET /users
	public List<User> getUsers() {
		this.listUsers = new ArrayList<User>();
		
		try {

			HttpURLConnection conn = this.absRequest.makeGetRequest();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String response;
			while ((response = br.readLine()) != null) {
				this.listUsers = this.jsonParser.listJsonToListUsers(response);
			}
			
			this.absRequest.closeHttpConnnection();
			

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		
		return this.listUsers;
	}

}
