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
	private Queue<BattleData> resultQueue;
	
	/** For the accepted battles interface */
	public List<Integer> visibilityList;
	public List<Integer> attackingClanList;
	
	public BattleManager()
	{
		pendingBattleList = new ArrayList<BattleData>();
		acceptedBattleList = new ArrayList<BattleData>();
		removalQueue = new LinkedList<BattleData>();
		aditionQueue = new LinkedList<BattleData>();
		resultQueue = new LinkedList<BattleData>();
		
		visibilityList = new ArrayList<Integer>();
		attackingClanList = new ArrayList<Integer>();
		
		/** DEBUG, idealmente seria pego e gravado em preferences */
		BattleData b1, b2, b3, b4;
		b1 = new BattleData(0, new Date(), 201);
		b1.setRegionData(new RegionData(201, "Planicie"));
		b2 = new BattleData(2, new Date(), 202);
		b2.setRegionData(new RegionData(202, "Montanha"));
		b3 = new BattleData(1, new Date(), 203);
		b3.setRegionData(new RegionData(203, "Ilha"));
		b4 = new BattleData(0, new Date(), 204);
		b4.setRegionData(new RegionData(204, "Floresta"));
		pendingBattleList.add(b1);
		pendingBattleList.add(b2);
		pendingBattleList.add(b3);
		pendingBattleList.add(b4);
	}
	
	public String removeOldBattles() 
	{
		String ret = "";
		
		for (BattleData item : pendingBattleList) 
		{
            if(!DateHelper.isItToday(item.getDate(), new Date()) || !DateHelper.checkTimeFrame(item.getTimeFrameId()))
            {
            	ret += "Batalha em \"" + item.getRegionData().getName() + "\" expirou �s " + DateHelper.getTimeLimitFromTimeframeID(item.getTimeFrameId()) + "!\n";
            	queueToRemove(pendingBattleList.get(pendingBattleList.indexOf(item)));
            }
        }
		removePendingBattles();
		
		return ret;
	}
	
	public String acceptedBattleHasResults()
	{
		String ret = "";
		
		for (BattleData item : acceptedBattleList) 
		{
            if((!DateHelper.isItToday(item.getDate(), new Date()) || !DateHelper.checkTimeFrame(item.getTimeFrameId())) && !resultQueue.contains(item))
            {
            	ret += "Batalha das " + DateHelper.getTimeLimitFromTimeframeID(item.getTimeFrameId()) + " em \"" + item.getRegionData().getName() + "\" tem resultados!\n";
            	queueToResult(item);
            }
        }
		
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

	private void queueToResult(BattleData battle)
	{
		resultQueue.add(battle);
	}
	
	private void queueToRemove(BattleData battle)
	{
		removalQueue.add(battle);
	}

	private void queueToAdd(BattleData battle)
	{
		aditionQueue.add(battle);
	}

	public Queue<BattleData> retrieveResults()
	{
		return resultQueue;
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
			if(item.getRegionId() == battleID)
			{
				queueToRemove(item);
				break;
			}
		}
		
		removePendingBattles();
	}	
}
