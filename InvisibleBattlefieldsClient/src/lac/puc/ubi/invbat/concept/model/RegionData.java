package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;

public class RegionData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int regionID;
	private String regionName;
	
	public RegionData(int _regionID, String _name)
	{
		regionID = _regionID;
		regionName = _name;
	}
	
	public int getRegionID() {
		return regionID;
	}
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
}
