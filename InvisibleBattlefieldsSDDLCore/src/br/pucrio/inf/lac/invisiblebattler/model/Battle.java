package br.pucrio.inf.lac.invisiblebattler.model;

import java.util.Date;

import br.pucrio.inf.lac.invisiblebattler.dao.RegionDAO;

public class Battle {
	private int id;
	private int timeFrameID;
	private Date date;
	private Region region;

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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setRegion(Integer Region_id) {
		RegionDAO dao = new RegionDAO();
		region = dao.buscar(Region_id);
	}

	public String toString() {
		String str = "Battle \nid: " + id + "\ntimeFrameID: " + timeFrameID
				+ "\ndate: " + date.toString() + "\nregion: "
				+ region.toString() + "\n---------";
		return str;
	}
}
