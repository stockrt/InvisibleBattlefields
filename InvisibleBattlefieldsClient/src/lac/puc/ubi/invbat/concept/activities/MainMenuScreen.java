package lac.puc.ubi.invbat.concept.activities;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.misc.AcceptedBattleArrayAdapter;
import lac.puc.ubi.invbat.concept.misc.PendingBattleArrayAdapter;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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
	private ListView m_listView;
	
	private ArrayAdapter<BattleData> adapter;
	private List<BattleData> battleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		state = "pending";
		
		ap = (InvBatApplication) getApplication();
		mRunnableHandler = new Handler();
		mRunnableHandler.post(mCheckPendingBattlesState);
		
		m_charName = (TextView) findViewById(R.id.txt_charname);
		m_level = (TextView) findViewById(R.id.txt_level);
		m_winStreak = (TextView) findViewById(R.id.txt_winstreak);
		
		m_listView = (ListView) findViewById(R.id.listview_menu);
		
		refreshCharValues();
		refreshListValues();
	}

	private void refreshListValues() 
	{
		if(state.equals("pending"))
		{
			battleList = ap.m_battleManager.retrievePendingBattleList();
			refreshPendingBattleValues();
			adapter = new PendingBattleArrayAdapter(this, battleList, ap);
			m_listView.setAdapter(adapter);
		}
		else if(state.equals("accepted"))
		{
			battleList = ap.m_battleManager.retrieveAcceptedBattleList();
			adapter = new AcceptedBattleArrayAdapter(this, battleList, ap);
			m_listView.setAdapter(adapter);
		}
		
	}

	private void refreshCharValues() 
	{
		m_charName.append(" " + ap.m_player.name + " ");
		m_level.append(" " + ap.m_player.level + " ");
		m_winStreak.append(" " + ap.m_player.num_victories + " ");
		m_winStreak.append(getResources().getText(R.string.sufix_winstreak));
	}
	
	private void refreshPendingBattleValues() 
	{
		String battleRemovalResult = ap.m_battleManager.removeOldBattles();
		if(!battleRemovalResult.equals(""))
			Toast.makeText(getBaseContext(), battleRemovalResult, Toast.LENGTH_LONG).show();
		
		ap.m_battleManager.addPendingBattles();
	}
	
	private Runnable mCheckPendingBattlesState = new Runnable() {

		public void run() {
			refreshPendingBattleValues();
			mRunnableHandler.postDelayed(this, 5000);
			adapter.notifyDataSetChanged();
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
			adapter.notifyDataSetChanged();
		}
		else if(state.equals("accepted"))
		{
			state = "pending";
			refreshListValues();
			adapter.notifyDataSetChanged();
		}
		
		return true;
	}
	
	@Override
	protected void onRestart() 
	{	
		super.onRestart();
		
		BattleData lastAcceptedBattle = ap.m_battleManager.peekLastAcceptedBattle();
		
		if(lastAcceptedBattle != null)
			ap.m_battleManager.removePendingBattleByID(lastAcceptedBattle.getBattleID());
		
		adapter.notifyDataSetChanged();
	}
}
