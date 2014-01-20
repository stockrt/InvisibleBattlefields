package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationRequest implements Serializable {

	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private double lat;
	private double lng;

	public LocationRequest(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLatitude() {
		return lat;
	}

	public void setLatitude(double lat) {
		this.lat = lat;
	}

	public double getLongitude() {
		return lng;
	}

	public void setLongitude(double lng) {
		this.lng = lng;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public String toString() {

		JSONObject result = new JSONObject();

		try {
			result.put("latitude", lat);
			result.put("longitude", lng);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
