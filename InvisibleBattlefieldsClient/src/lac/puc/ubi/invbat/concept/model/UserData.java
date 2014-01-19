package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;

public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private CharacterData charData;

	public UserData(String _email, String _pass) {
		this.email = _email;
		this.password = _pass;
		charData = null;
	}
	
	public UserData(String _email, String _pass, CharacterData _charData) {
		this.email = _email;
		this.password = _pass;
		this.charData = _charData;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public CharacterData getCharData() {
		return charData;
	}

	public void setCharData(CharacterData charData) {
		this.charData = charData;
	}
}
