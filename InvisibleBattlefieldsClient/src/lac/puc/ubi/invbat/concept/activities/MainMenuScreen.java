package lac.puc.ubi.invbat.concept.activities;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.misc.BattleArrayAdapter;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuScreen extends Activity {

	private InvBatApplication ap;
	
	private TextView m_charName;
	private TextView m_level;
	private TextView m_winStreak;
	private ListView m_listView;
	
	private BattleArrayAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ap = (InvBatApplication) getApplication();
		
		m_charName = (TextView) findViewById(R.id.txt_charname);
		m_level = (TextView) findViewById(R.id.txt_level);
		m_winStreak = (TextView) findViewById(R.id.txt_winstreak);
		
		m_listView = (ListView) findViewById(R.id.listview_menu);
		
		refreshCharValues();
		
		final List<BattleData> list = refreshPendingBattleValues();
		adapter = new BattleArrayAdapter(this, list, ap);
		m_listView.setAdapter(adapter);
	}

	private void refreshCharValues() 
	{
		m_charName.append(" " + ap.m_player.name + " ");
		m_level.append(" " + ap.m_player.level + " ");
		m_winStreak.append(" " + ap.m_player.num_victories + " ");
		m_winStreak.append(getResources().getText(R.string.sufix_winstreak));
	}
	
	private List<BattleData> refreshPendingBattleValues() 
	{
		List<BattleData> battleList = ap.m_battleManager.refreshBattleValues();
		Toast.makeText(getBaseContext(), ap.m_battleManager.removeOldBattles(), Toast.LENGTH_SHORT).show();
		
		return battleList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
}
