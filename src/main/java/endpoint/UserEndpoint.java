package endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import model.User;
import util.JsonParser;

public class UserEndpoint extends AbstractRequest implements Serializable{

	private static final long serialVersionUID = -8578600928021044681L;

	private JsonParser jsonParser;

	public UserEndpoint() {
		super();
		this.jsonParser = new JsonParser();
	}


	// GET /users
	public List<User> getUsers() {
		List<User> listUsers = new ArrayList<User>();

		try {

			HttpURLConnection conn = this.makeGetRequest();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String response;
			while ((response = br.readLine()) != null) {
				listUsers = this.jsonParser.listJsonToListUsers(response);
			}

			this.closeHttpConnnection();


		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return listUsers;
	}

	/**
	 * Add User
	 */
	public void postUser(User user) {
		try {

			HttpURLConnection conn = this.makePostRequest();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			// TODO POST, PUT AND GET by ID 
			JSONObject jsonObj = this.jsonParser.userToJson(user);
			// Add jsonObj no body

			byte[] postDataBytes = jsonObj.toString().getBytes("UTF-8");

			conn.getOutputStream().write(postDataBytes);

			this.closeHttpConnnection();


		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
