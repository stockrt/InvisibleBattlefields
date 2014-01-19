package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.model.CharacterData;

public class LoginResponse implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	/** Authentication answer to the client */
	private Boolean authAnswer;
	
	/** Retrieved character data for the client */
	private CharacterData chardata;
	
	//TODO: falta lista de batalhas pendentes salvas, retornar aqui, depois, ou lidar somente client side?
	
	/**
	 * Constructor.
	 **/
	public LoginResponse(UUID _id, Boolean _answer, CharacterData _data) {
		uuid = _id;
		authAnswer = _answer;
		chardata = _data;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Boolean getAuthAnswer() {
		return authAnswer;
	}

	public void setAuthAnswer(Boolean authAnswer) {
		this.authAnswer = authAnswer;
	}

	public CharacterData getChardata() {
		return chardata;
	}

	public void setChardata(CharacterData chardata) {
		this.chardata = chardata;
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

			info.put("authanswer", authAnswer);
			info.put("chardata", chardata.toString());
			
			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}*/
}
