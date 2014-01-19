package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import lac.puc.ubi.invbat.concept.model.UserData;

public class LoginRequest implements Serializable{

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** User Info for Login */
	private UserData userData;

	/**
	 * Constructor.
	 **/
	public LoginRequest(String _email, String _pass) {
		userData = new UserData(_email, _pass);
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
