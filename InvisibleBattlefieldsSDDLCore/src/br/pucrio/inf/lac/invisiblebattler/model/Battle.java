package br.pucrio.inf.lac.invisiblebattler.model;

import java.util.Date;

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

	public String toString() {
		String str = "\nid: " + id + "\ntimeFrameID: " + timeFrameID
				+ "\ndate: " + date.toString() + "\nregion: "
				+ region.toString();
		return str;
	}
}
