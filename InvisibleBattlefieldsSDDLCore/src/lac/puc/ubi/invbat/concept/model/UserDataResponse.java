package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDataResponse implements Serializable {

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;

	private Boolean authAnswer;
	private CharacterData chardata;

	/**
	 * Constructor.
	 **/
	public UserDataResponse(UUID _id, Boolean _answer, CharacterData _data) {
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
	@Override
	public String toString() {

		JSONObject result = new JSONObject();
		JSONObject info = new JSONObject();

		try {
			result.put("uuid", uuid.toString());

			info.put("authanswer", authAnswer);
			if (chardata != null) {
				info.put("chardata", chardata.toString());
			}
			else {
				info.put("chardata", "null");
			}

			result.put("info", info.toString());

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return result.toString();
	}
}
