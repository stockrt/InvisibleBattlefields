package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDataRequest implements Serializable{

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	/** Authentication Info */
	private String email;
	private String password; //not stored
	
	/**
	 * Constructor.
	 **/
	public UserDataRequest(UUID _id, String _email, String _pass) {
		uuid = _id;
		email = _email;
		password = _pass;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getTypeName() {
		return "authNode";
	}
	
	/**
     * {@inheritDoc}
     **/
    @Override
    public String toString() {

    	JSONObject result = new JSONObject();
//    	JSONObject info = new JSONObject();
		
		try {
			result.put("uuid", uuid.toString());
			result.put("name", email);
			result.put("pass", password);

//			info.put("password", password);
			
//			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}
    
	
}
