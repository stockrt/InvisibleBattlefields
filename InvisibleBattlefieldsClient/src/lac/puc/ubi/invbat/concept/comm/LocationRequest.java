package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

public class LocationRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;
	
	/** Location coordinates */
	private double lat;
	private double lng;
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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
    /*@Override
    public String toString() {

    	JSONObject result = new JSONObject();
    	JSONObject info = new JSONObject();
		
		try {
			result.put("uuid", uuid.toString());

			info.put("latitude", lat);
			info.put("longitude", lng);
			
			result.put("info", info.toString());
			
			} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return result.toString();
	}*/
}
