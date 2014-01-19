package lac.puc.ubi.invbat.concept.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
		
		pendingBattleList.add(new BattleData(100, 0, new Date(), new RegionData(201, "Planicie")));
		pendingBattleList.add(new BattleData(101, 2, new Date(), new RegionData(202, "Montanha")));
		pendingBattleList.add(new BattleData(102, 1, new Date(), new RegionData(203, "Ilha")));
		pendingBattleList.add(new BattleData(103, 0, new Date(), new RegionData(204, "Floresta")));
		
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
