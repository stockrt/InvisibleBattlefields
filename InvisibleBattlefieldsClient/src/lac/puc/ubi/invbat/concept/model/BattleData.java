package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class BattleData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*UUID-batalha: (timeFrame, date, regiao)
	0:  00h <--> 08h
	1:  8h <--> 16h
	2:  16h <--> 00h*/
	private UUID battleID;
	
	private int timeFrameID;
	private Date date;
	private RegionData regionData;

	public BattleData(UUID _uuid, int _timeID, Date _date, RegionData _rData)
	{
		battleID = _uuid;
		timeFrameID = _timeID;
		date = _date;
		regionData = _rData;
	}
	
	public UUID getBattleID() {
		return battleID;
	}

	public void setBattleID(UUID battleID) {
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
