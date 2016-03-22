package endpoint;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class AbstractRequest implements Serializable{
	
	private static final long serialVersionUID = -4194861133459206734L;

	private HttpURLConnection conn;
	
	private URL url;
	
	public AbstractRequest() {
		super();
		try {
			this.url = new URL("http://localhost:8080/userServer/users");
			this.conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			this.conn = null;
			e.printStackTrace();
		}
	}

	public HttpURLConnection makeGetRequest(){
		try {
			this.conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		this.conn.setRequestProperty("Accept", "application/json");
		
		return this.conn;
		
		
	}
	
	//TODO get with params, post and put
	
	public void closeHttpConnnection(){
		this.conn.disconnect();
	}

}
