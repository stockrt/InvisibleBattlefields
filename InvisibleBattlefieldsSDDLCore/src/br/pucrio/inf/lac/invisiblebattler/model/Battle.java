package br.pucrio.inf.lac.invisiblebattler.model;

import java.util.Date;

import org.json.JSONObject;

import lac.puc.ubi.invbat.concept.dao.RegionDAO;
import lac.puc.ubi.invbat.concept.model.RegionData;

public class Battle {
	private int id;
	private int timeFrameId;
	private Date date;
	private RegionData region;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimeFrameID() {
		return timeFrameID;
	}

	public void setTimeFrameID(int timeFrameID) {
		this.timeFrameID = timeFrameID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RegionData getRegion() {
		return region;
	}

	public void setRegion(RegionData region) {
		this.region = region;
	}

	public void setRegion(Integer Region_id) {
		RegionDAO dao = new RegionDAO();
		region = dao.buscar(Region_id);
	}

	public String toString() {
		JSONObject result = new JSONObject();
		result.put("timeFrameID", timeFrameID);
		String str = "Battle \nid: " + id + "\ntimeFrameID: " + timeFrameID
				+ "\ndate: " + date.toString() + "\nregion: "
				+ region.toString() + "\n---------";
		return str;
	}
}
