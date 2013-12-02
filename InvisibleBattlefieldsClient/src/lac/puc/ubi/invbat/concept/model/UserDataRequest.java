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
	
	/* Game Related Info */
	private int clan_id;
	private CharacterData character;
	
	/**
	 * Constructor.
	 **/
	public UserDataRequest(UUID _id, String _name, String _pass, int _clan_id, CharacterData _chr) {
		uuid = _id;
		email = _name;
		password = _pass;
		clan_id = _clan_id;
		character = _chr;
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
	
	public CharacterData getCharacterData() { //isso é o suficiente, ou é melhor fazer um parse p/ String?
		return character;
	}
	
	public int getClanID() {
		return clan_id;
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
			info.put("clan", clan_id);
			info.put("character", character.toString());
			
			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}
    
	
}
