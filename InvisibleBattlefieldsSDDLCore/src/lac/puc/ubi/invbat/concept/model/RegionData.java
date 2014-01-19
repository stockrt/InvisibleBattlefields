package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.UUID;

public class RegionData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID regionID; //TODO: Pode ter um RegionRequest/Response para mostrar a região no mapa do usuário com um polígono
	private String regionName;
	
	public RegionData(UUID _uuid, String _name)
	{
		regionID = _uuid;
		regionName = _name;
	}
	
	public UUID getRegionID() {
		return regionID;
	}
	public void setRegionID(UUID regionID) {
		this.regionID = regionID;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
}
