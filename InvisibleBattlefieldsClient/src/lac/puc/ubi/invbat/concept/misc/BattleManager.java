package lac.puc.ubi.invbat.concept.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.RegionData;

public class BattleManager {

	private List<BattleData> pendingBattleList;
	private List<BattleData> acceptedBattleList;
	private Queue<BattleData> removalQueue;
	private Queue<BattleData> aditionQueue;
	
	public BattleManager()
	{
		pendingBattleList = new ArrayList<BattleData>();
		acceptedBattleList = new ArrayList<BattleData>();
		removalQueue = new LinkedList<BattleData>();
		aditionQueue = new LinkedList<BattleData>();
		
		/** DEBUG, idealmente seria pego e gravado em preferences */
		pendingBattleList.add(new BattleData(100, 0, new Date(), new RegionData(201, "Planicie")));
		pendingBattleList.add(new BattleData(101, 2, new Date(), new RegionData(202, "Montanha")));
		pendingBattleList.add(new BattleData(102, 1, new Date(), new RegionData(203, "Ilha")));
		pendingBattleList.add(new BattleData(103, 0, new Date(), new RegionData(204, "Floresta")));
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
	
	public List<BattleData> retrievePendingBattleList()
	{		
		return pendingBattleList;
	}
	
	public List<BattleData> retrieveAcceptedBattleList()
	{
		return acceptedBattleList;
	}
	
	private void queueToRemove(BattleData battle)
	{
		removalQueue.add(battle);
	}

	private void queueToAdd(BattleData battle)
	{
		aditionQueue.add(battle);
	}

	public void removePendingBattles()
	{
		for(BattleData item : removalQueue)
		{
			removePendingBattle(item);
		}
		
		removalQueue.clear();
	}
	
	public void addPendingBattles()
	{
		for(BattleData item : aditionQueue)
		{
			pendingBattleList.add(item);
		}
		
		aditionQueue.clear();
	}

	public void removePendingBattle(BattleData thisBattle) {
		pendingBattleList.remove(thisBattle);
	}

	public void newPendingBattle(BattleData battle) 
	{
		queueToAdd(battle);
	}

	public void newAcceptedBattle(BattleData battle) 
	{
		acceptedBattleList.add(battle);
	}


	public BattleData peekLastAcceptedBattle() 
	{
		BattleData ret = null;
		
		if(!acceptedBattleList.isEmpty())
			ret = acceptedBattleList.get(acceptedBattleList.size() - 1);
		
		return ret;
	}


	public void removePendingBattleByID(int battleID) 
	{
		for(BattleData item : pendingBattleList)
		{
			if(item.getBattleID() == battleID)
			{
				queueToRemove(item);
				break;
			}
		}
		
		removePendingBattles();
	}	
}
