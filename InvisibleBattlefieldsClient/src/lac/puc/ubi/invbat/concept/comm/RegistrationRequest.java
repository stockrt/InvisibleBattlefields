package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.model.CharacterData;
import lac.puc.ubi.invbat.concept.model.UserData;

public class RegistrationRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	/** User Info for Registration */
	private UserData userData;
	
	/** 
	 * Constructor
	 **/
	public RegistrationRequest(UUID _id, String _email, String _charName, String _pass, int _clanId) {
		uuid = _id;
		userData = new UserData(_email, _pass, new CharacterData(_charName, _clanId));
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public UserData getUserData() {
		return userData;
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
