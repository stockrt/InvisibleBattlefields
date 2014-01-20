package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;

import lac.puc.ubi.invbat.concept.dao.RegionDAO;

import org.json.JSONException;
import org.json.JSONObject;

public class BattleData implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * UUID-batalha: (timeFrame, date, regiao) 0: 00h <--> 08h 1: 8h <--> 16h 2:
	 * 16h <--> 00h
	 */
	private int id;
	private int timeFrameId;
	private Date date;
	private int regionId;
	private RegionData regionData;

	public BattleData(int timeFrameId, Date date, int regionId) {
		super();
		this.timeFrameId = timeFrameId;
		this.date = date;
		this.regionId = regionId;
		regionData = null;
	}

	public BattleData() {
		super();
		this.timeFrameId = 0;
		this.date = new Date();
		this.regionId = 0;
		regionData = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimeFrameId() {
		return timeFrameId;
	}

	public void setTimeFrameId(int timeFrameId) {
		this.timeFrameId = timeFrameId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public RegionData getRegionData() {
		if (regionData == null) {
			RegionDAO dao = new RegionDAO();
			regionData = dao.buscar(regionId);
		}

		return regionData;
	}

	public void setRegionData(RegionData regionData) {
		this.regionData = regionData;
	}

	public String toString() {
		JSONObject result = new JSONObject();
		try {
			result.put("id", id);
			result.put("timeFrameId", timeFrameId);
			result.put("date",date.toString());
			result.put("regionId",regionId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}

}
