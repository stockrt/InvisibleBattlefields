package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.model.UserData;

public class LoginRequest implements Serializable{

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	/** User Info for Login */
	private UserData userData;

	/**
	 * Constructor.
	 **/
	public LoginRequest(UUID _id, String _email, String _pass) {
		uuid = _id;
		userData = new UserData(_email, _pass);
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	/**
     * {@inheritDoc}
     **/
    /*@Override
    public String toString() {

    	JSONObject result = new JSONObject();
    	JSONObject info = new JSONObject();
		
		try {
			result.put("uuid", uuid.toString());

			info.put("userdata", userData.toString());
			
			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}*/
}
