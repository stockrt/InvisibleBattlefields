package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	/** Authentication Info */
	private String email;
	private String password;

	private CharacterData charData;
	
	/**
	 * Constructor.
	 **/
	public RegistrationRequest(UUID _id, String _email, String _charName, String _pass, int _clanId) {
		uuid = _id;
		email = _email;
		password = _pass;
		charData = new CharacterData(_charName, _clanId);
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CharacterData getCharData() {
		return charData;
	}

	public void setCharData(CharacterData charData) {
		this.charData = charData;
	}

	/**
     * {@inheritDoc}
     **/
    @Override
    public String toString() {

    	JSONObject result = new JSONObject();
    	JSONObject info = new JSONObject();
		
		try {
			result.put("uuid", uuid.toString());
			result.put("name", email);

			info.put("password", password);
			info.put("chardata", charData.toString());
			
			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}
}
