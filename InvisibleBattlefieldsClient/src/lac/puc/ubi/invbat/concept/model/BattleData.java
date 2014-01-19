package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;

public class BattleData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*ID-batalha: (timeFrame, date, regiao)
	0:  00h <--> 08h
	1:  8h <--> 16h
	2:  16h <--> 00h*/
	private int battleID;
	
	private int timeFrameID;
	private Date date;
	private RegionData regionData;

	public BattleData(int _battleID, int _timeID, Date _date, RegionData _rData)
	{
		battleID = _battleID;
		timeFrameID = _timeID;
		date = _date;
		regionData = _rData;
	}
	
	public int getBattleID() {
		return battleID;
	}

	public void setBattleID(int battleID) {
		this.battleID = battleID;
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

	public RegionData getRegionData() {
		return regionData;
	}

	public void setRegionData(RegionData regionData) {
		this.regionData = regionData;
	}
}
