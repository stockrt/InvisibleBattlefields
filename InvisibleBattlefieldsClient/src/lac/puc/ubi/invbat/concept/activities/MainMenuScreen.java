package lac.puc.ubi.invbat.concept.activities;

import java.util.List;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.misc.BattleArrayAdapter;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuScreen extends Activity {

	private InvBatApplication ap;
	
	private TextView m_charName;
	private TextView m_level;
	private TextView m_winStreak;
	private ListView m_listView;
	
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
		
		final List list = refreshPendingBattleValues();
		final BattleArrayAdapter adapter = new BattleArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		m_listView.setAdapter(adapter);
		
		m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
			    final String item = (String) parent.getItemAtPosition(position);
			    view.animate().setDuration(2000).alpha(0)
			        .withEndAction(new Runnable() {
			        	@Override
			        	public void run() 
			        	{
			        		list.remove(item);
			        		adapter.notifyDataSetChanged();
			        		view.setAlpha(1);
			        	}
			        });
			}
		});
	}

	private void refreshCharValues() 
	{
		m_charName.append(" " + ap.player.name + " ");
		m_level.append(" " + ap.player.level + " ");
		m_winStreak.append(" " + ap.player.num_victories + " ");
		m_winStreak.append(getResources().getText(R.string.sufix_winstreak));
	}
	
	private List refreshPendingBattleValues() 
	{
		List battleList = ap.refreshBattleValues();
		Toast.makeText(getBaseContext(), ap.removeOldBattles(), Toast.LENGTH_SHORT).show();
		
		return battleList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
}
