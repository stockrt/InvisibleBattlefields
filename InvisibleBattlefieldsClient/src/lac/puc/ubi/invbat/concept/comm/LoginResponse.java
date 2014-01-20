package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import lac.puc.ubi.invbat.concept.model.CharacterData;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginResponse implements Serializable {

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	private Boolean authAnswer;
	private CharacterData chardata;

	/**
	 * Constructor.
	 **/
	public LoginResponse(Boolean _answer, CharacterData _data) {
		authAnswer = _answer;
		chardata = _data;
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

		try {

			result.put("authanswer", authAnswer);
			if (chardata != null) {
				result.put("chardata", chardata.toString());
			} else {
				result.put("chardata", "null");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
