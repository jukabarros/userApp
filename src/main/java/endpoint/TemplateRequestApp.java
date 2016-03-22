package endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.JsonParser;

public class TemplateRequestApp {
	
	public static void main(String[] args) {
		
		List<User> listUsers = new ArrayList<User>();
		
		AbstractRequest absRequest = new AbstractRequest();
		try {
			
			HttpURLConnection conn = absRequest.makeGetRequest();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			JsonParser jp = new JsonParser();
			String output;
			System.out.println("************* Server Response ************");
			while ((output = br.readLine()) != null) {
				listUsers = jp.listJsonToListUsers(output);
			}

			absRequest.closeHttpConnnection();	
			
			System.out.println(listUsers);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
