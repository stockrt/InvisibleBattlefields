package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import lac.puc.ubi.invbat.concept.model.CharacterData;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationRequest implements Serializable {

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	private CharacterData charData;

	/**
	 * Constructor.
	 **/
	public RegistrationRequest(String _email, String _charName, String _pass,
			int _clanId) {
		charData = new CharacterData(_charName, _clanId);
		charData.setEmail(_email);
		charData.setPassword(_pass);
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

		try {
			result.put("char", charData.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
