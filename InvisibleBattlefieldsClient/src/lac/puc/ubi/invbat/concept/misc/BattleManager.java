package lac.puc.ubi.invbat.concept.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.RegionData;

public class BattleManager {

	public List<BattleData> pendingBattleList;
	private Queue<BattleData> removalQueueIndex;
	
	public BattleManager()
	{
		pendingBattleList = new ArrayList<BattleData>();
		removalQueueIndex = new LinkedList<BattleData>();
	}
	
	
	public String removeOldBattles() {
		String ret = "";
		
		for (BattleData item : pendingBattleList) 
		{
            if(!DateHelper.isItToday(item.getDate(), new Date()) || !DateHelper.checkTimeFrame(item.getTimeFrameID()))
            {
            	ret += "Batalha em \"" + item.getRegionData().getRegionName() + "\" expirou às " + DateHelper.getTimeLimitFromTimeframeID(item.getTimeFrameID()) + "!\n";
            	queueToRemove(pendingBattleList.get(pendingBattleList.indexOf(item)));
            }
        }
		removePendingBattles();
		
		return ret;
	}

	public List<BattleData> refreshBattleValues() {
		//TODO: faz request das pending battle
		
		pendingBattleList.add(new BattleData(new UUID(2,1), 0, new Date(), new RegionData(new UUID(3,1), "Planicie")));
		pendingBattleList.add(new BattleData(new UUID(2,2), 2, new Date(), new RegionData(new UUID(3,2), "Montanha")));
		pendingBattleList.add(new BattleData(new UUID(2,3), 1, new Date(), new RegionData(new UUID(3,3), "Ilha")));
		pendingBattleList.add(new BattleData(new UUID(2,3), 0, new Date(), new RegionData(new UUID(3,4), "Floresta")));
		
		return pendingBattleList;
	}
	
	private void queueToRemove(BattleData battle)
	{
		removalQueueIndex.add(battle);
	}
	
	public void removePendingBattles()
	{
		for(BattleData item : removalQueueIndex)
		{
			removePendingBattle(item);
		}
		
		removalQueueIndex.clear();
	}

	public void removePendingBattle(BattleData thisBattle) {
		pendingBattleList.remove(thisBattle);
	}	
}
