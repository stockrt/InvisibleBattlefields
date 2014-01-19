package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;

import br.pucrio.inf.lac.invisiblebattler.model.Battle;

public class BattleData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*UUID-batalha: (timeFrame, date, regiao)
	0:  00h <--> 08h
	1:  8h <--> 16h
	2:  16h <--> 00h*/
	private int battleID;
	
	private int timeFrameID;
	private Date date;
	
	private RegionData regionData;

	public BattleData(Battle _battle) {
		battleID = _battle.getId();
		timeFrameID = _battle.getTimeFrameID();
		date = _battle.getDate();
		
				
	}
	public BattleData(int _uuid, int _timeID, Date _date, RegionData _rData)
	{
		battleID = _uuid;
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
