package lac.puc.ubi.invbat.concept.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.misc.DateHelper;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import lac.puc.ubi.invbat.concept.model.RegionData;
import android.app.Application;

/* Like a Registry game class */
public class InvBatApplication extends Application {

	public CharacterData player;
	public List<BattleData> pendingBattleList = new ArrayList<BattleData>();
	
	/* Temp */
	public void validateCharData() {
		//TODO: faz request da character data
		
		player = new CharacterData("Tobias the Great", 0);
	}

	public String removeOldBattles() {
		String ret = "";
		
		for (BattleData item : pendingBattleList) 
		{
            if(DateHelper.isItToday(item.getDate(), new Date()) && DateHelper.checkTimeFrame(item.getTimeFrameID()))
            {
            	ret.concat("Batalha em " + item.getRegionData().getRegionName() + "ja aconteceu!\n" );
            }
        }
		
		return ret;
	}

	public List refreshBattleValues() {
		//TODO: faz request das pending battles

		pendingBattleList.add(new BattleData(new UUID(2,1), 0, new Date(), new RegionData(new UUID(3,1), "Planicie")));
		pendingBattleList.add(new BattleData(new UUID(2,2), 2, new Date(), new RegionData(new UUID(3,2), "Montanha")));
		pendingBattleList.add(new BattleData(new UUID(2,3), 1, new Date(), new RegionData(new UUID(3,3), "Ilha")));
		pendingBattleList.add(new BattleData(new UUID(2,3), 0, new Date(), new RegionData(new UUID(3,4), "Floresta")));
		
		return pendingBattleList;
	}	
}
