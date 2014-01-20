package lac.puc.ubi.invbat.concept.activities;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.misc.AcceptedBattleArrayAdapter;
import lac.puc.ubi.invbat.concept.misc.LocationService;
import lac.puc.ubi.invbat.concept.misc.PendingBattleArrayAdapter;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuScreen extends Activity {

	static private String state;
	
	private InvBatApplication ap;
	private Handler mRunnableHandler;
	
	private TextView m_charName;
	private TextView m_level;
	private TextView m_winStreak;
	private TextView m_mainatt;
	private TextView m_clanname;
	private ListView m_listView;

	private PendingBattleArrayAdapter pendingAdapter;
	private AcceptedBattleArrayAdapter acceptedAdapter;
	private List<BattleData> battleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/** Location Service */
		ComponentName comp = new ComponentName(getPackageName(), LocationService.class.getName());
		ComponentName service = startService(new Intent().setComponent(comp));
		
		state = "pending";
		
		ap = (InvBatApplication) getApplication();
		mRunnableHandler = new Handler();
		mRunnableHandler.post(mCheckBattleState);
		
		m_charName = (TextView) findViewById(R.id.txt_charname);
		m_level = (TextView) findViewById(R.id.txt_level);
		m_winStreak = (TextView) findViewById(R.id.txt_winstreak);
		m_clanname = (TextView) findViewById(R.id.txt_clanname);
		m_mainatt = (TextView) findViewById(R.id.txt_mainatt);
		
		m_listView = (ListView) findViewById(R.id.listview_menu);
		
		refreshCharValues();
		refreshListValues();

		pendingAdapter = new PendingBattleArrayAdapter(this, ap.m_battleManager.retrievePendingBattleList(), ap);
		acceptedAdapter = new AcceptedBattleArrayAdapter(this, ap.m_battleManager.retrieveAcceptedBattleList(), ap);
	}

	private void refreshListValues() 
	{
		pendingAdapter = new PendingBattleArrayAdapter(this, ap.m_battleManager.retrievePendingBattleList(), ap);
		acceptedAdapter = new AcceptedBattleArrayAdapter(this, ap.m_battleManager.retrieveAcceptedBattleList(), ap);

		if(state.equals("pending"))
		{
			battleList = ap.m_battleManager.retrievePendingBattleList();
			refreshPendingBattleValues();
			m_listView.setAdapter(pendingAdapter);
		}
		else if(state.equals("accepted"))
		{
			battleList = ap.m_battleManager.retrieveAcceptedBattleList();
			m_listView.setAdapter(acceptedAdapter);
		}
		
	}

	private void refreshCharValues() 
	{
		m_charName.append(" " + ap.m_player.getName() + " ");
		m_level.append(" " + ap.m_player.getLevel()  + " ");
		m_clanname.append(getResources().getStringArray(R.array.clan_name_array)[ap.m_player.getClanId()]);
		m_winStreak.append(" " + ap.m_player.getNum_victories() + " ");
		m_winStreak.append(getResources().getText(R.string.sufix_winstreak));
		
		m_mainatt.append(ap.m_player.getMainAttLbl() + ": " + ap.m_player.getMainAttValue());
		char c = ap.m_player.getMainAttLbl().charAt(0);
		switch(c)
		{
			case 'S':
				m_mainatt.setTextColor(Color.RED);
				break;
			case 'A':
				m_mainatt.setTextColor(Color.GREEN);
				break;
			case 'I':
				m_mainatt.setTextColor(Color.BLUE);
				break;
		}
	}
	
	private void refreshPendingBattleValues() 
	{
		String battleRemovalResult = ap.m_battleManager.removeOldBattles();
		if(!battleRemovalResult.equals(""))
			Toast.makeText(getBaseContext(), battleRemovalResult, Toast.LENGTH_LONG).show();
		
		ap.m_battleManager.addPendingBattles();
	}
	
	private void refreshAcceptedBattleValues() 
	{
		String battleResult = ap.m_battleManager.acceptedBattleHasResults();
		if(!battleResult.equals(""))
			Toast.makeText(getBaseContext(), battleResult, Toast.LENGTH_LONG).show();
		
		for(BattleData item : ap.m_battleManager.retrieveResults())
		{
			acceptedAdapter.setVisibilityOfView(battleList.indexOf(item), true);
		}
	}
	
	private Runnable mCheckBattleState = new Runnable() {

		public void run() 
		{
			refreshPendingBattleValues();
			refreshAcceptedBattleValues();
			mRunnableHandler.postDelayed(this, 5000);
			pendingAdapter.notifyDataSetChanged();
			acceptedAdapter.notifyDataSetChanged();
    	}
    };
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if(state.equals("pending"))
		{
			state = "accepted";
			refreshListValues();
			pendingAdapter.notifyDataSetChanged();
		}
		else if(state.equals("accepted"))
		{
			state = "pending";
			refreshListValues();
			acceptedAdapter.notifyDataSetChanged();
		}
		
		return true;
	}
	
	@Override
	protected void onRestart() 
	{	
		super.onRestart();
		
		BattleData lastAcceptedBattle = ap.m_battleManager.peekLastAcceptedBattle();
		
		if(lastAcceptedBattle != null)
			ap.m_battleManager.removePendingBattleByID(lastAcceptedBattle.getRegionId());

		pendingAdapter.notifyDataSetChanged();
		acceptedAdapter.notifyDataSetChanged();
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{     
		super.onActivityResult(requestCode, resultCode, data); 

		refreshListValues();
		
		int clanId;
		
		switch(requestCode) 
		{ 
		    case (1): 
		    	if (resultCode == Activity.RESULT_OK) 
		    	{ 
		    		clanId = data.getIntExtra("clanid", 0);
					acceptedAdapter.setClanIconOfView(acceptedAdapter.getPositionFromObj(ap.m_battleManager.peekLastAcceptedBattle()), clanId);
		    	} 
		      	break; 
		} 
	}
	
	public void ShowCharacterStats(View v)
	{
		String stats = "";

		stats += "STR: " + ap.m_player.getAttributeStrength() + "\n";
		stats += "AGI: " + ap.m_player.getAttributeAgility() + "\n";
		stats += "INT: " + ap.m_player.getAttributeIntelligence() + "\n";
		
		Toast.makeText(getBaseContext(), stats, Toast.LENGTH_LONG).show();
	}
}
