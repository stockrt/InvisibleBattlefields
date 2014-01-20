package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest implements Serializable {

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Authentication Info */
	private String email;
	private String password; // not stored

	/**
	 * Constructor.
	 **/
	public LoginRequest(String _email, String _pass) {
		email = _email;
		password = _pass;
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

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public String toString() {

		JSONObject result = new JSONObject();
		try {
			result.put("name", email);
			result.put("pass", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
