package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import lac.puc.ubi.invbat.concept.model.CharacterData;
import lac.puc.ubi.invbat.concept.model.UserData;

public class RegistrationRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** User Info for Registration */
	private UserData userData;
	
	/** 
	 * Constructor
	 **/
	public RegistrationRequest(String _email, String _charName, String _pass, int _clanId) {
		userData = new UserData(_email, _pass, new CharacterData(_charName, _clanId));
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public UserData getUserData() {
		return userData;
	}
}
