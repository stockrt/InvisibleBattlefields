package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import ContextNetGeo.CtxCoordinate;
import ContextNetGeo.Polygon;

public class RegionData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1874158329749728660L;
	private int id;
	private String name;
	private String strPoints;

	public RegionData() {
		id = 0;
		name = strPoints = "";
	}
	
	public RegionData(int _id, String _name)
	{
		id = _id;
		name = _name;
		strPoints = "";
	}

	public RegionData(int _id, String _name, String _points) {
		id = _id;
		name = _name;
		strPoints = _points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrPoints() {
		return strPoints;
	}

	public void setStrPoints(String strPoints) {
		this.strPoints = strPoints;
	}

	public Polygon getPolygon() {
		String[] pp = strPoints.split("I");
		ArrayList<CtxCoordinate> listaCoordenadas = new ArrayList<CtxCoordinate>();
		for (int i = 0; i < pp.length; i++) {
			String latlng = pp[i];
			String[] point = latlng.split(";");
			Double lat = Double.valueOf(point[0]);
			Double lng = Double.valueOf(point[1]);
			listaCoordenadas.add(new CtxCoordinate(lat, lng));
		}
		return new Polygon(listaCoordenadas);
	}

	public String toString() {
		JSONObject result = new JSONObject();
		try {
			result.put("id", id);
			result.put("name", name);
			result.put("strPoints", strPoints);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
