package lac.puc.ubi.invbat.concept.activities;

import lac.puc.ubi.invbat.concept.app.InvBatApplication;
import lac.puc.ubi.invbat.concept.comm.FightResponse;
import lac.puc.ubi.invbat.concept.connection.CommunicationTask;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invisiblebattlefields.concept.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class BattleScreen extends Activity {

	private InvBatApplication ap;
	
	private int myClanID;
	private BattleData thisbattle;
	private ImageButton imgbtnClan_1;
	private ImageButton imgbtnClan_2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		ap = (InvBatApplication) getApplication();
		
        Bundle bundle = getIntent().getExtras();
        myClanID = bundle.getInt("clanID");
        thisbattle = (BattleData) bundle.getSerializable("battledata");
        
		imgbtnClan_1 = (ImageButton) findViewById(R.id.imgbtn_clan1);
		imgbtnClan_2 = (ImageButton) findViewById(R.id.imgbtn_clan2);
		
		setButtonProperties();
	}
	
	private void setButtonProperties() 
	{
		OnClickListener attackUnion = new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(getBaseContext(), "Attack Union!", Toast.LENGTH_SHORT).show();
			    ap.m_battleManager.newAcceptedBattle(thisbattle);
			    
			    CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "fight", new FightResponse(0, thisbattle.getId(), true, myClanID));
				task.execute();
			    
				finish();
			}
		};
		
		OnClickListener attackMercenariers = new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(getBaseContext(), "Attack Mercenaries!", Toast.LENGTH_SHORT).show();
			    ap.m_battleManager.newAcceptedBattle(thisbattle);
			    
			    CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "fight", new FightResponse(0, thisbattle.getId(), true, myClanID));
				task.execute();
			    
			    finish();
			}
		};
		
		OnClickListener attackBerserkers = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getBaseContext(), "Attack Berserkers!", Toast.LENGTH_SHORT).show();
			    ap.m_battleManager.newAcceptedBattle(thisbattle);

			    CommunicationTask task = new CommunicationTask(ap.getMessageHandler(), ap, "fight", new FightResponse(0, thisbattle.getId(), true, myClanID));
				task.execute();
			    
			    finish();
			}
		};
		
		switch(myClanID)
		{
			case 0:
				imgbtnClan_1.setImageResource(R.drawable.mercenaries);
				imgbtnClan_1.setOnClickListener(attackMercenariers);
				imgbtnClan_2.setImageResource(R.drawable.berserk);
				imgbtnClan_2.setOnClickListener(attackBerserkers);
				break;
			case 1:
				imgbtnClan_1.setImageResource(R.drawable.union);
				imgbtnClan_1.setOnClickListener(attackUnion);
				imgbtnClan_2.setImageResource(R.drawable.berserk);
				imgbtnClan_2.setOnClickListener(attackBerserkers);
				break;
			case 2:
				imgbtnClan_1.setImageResource(R.drawable.union);
				imgbtnClan_1.setOnClickListener(attackUnion);
				imgbtnClan_2.setImageResource(R.drawable.mercenaries);
				imgbtnClan_2.setOnClickListener(attackMercenariers);
				break;
			
			default:
				
		}
	}
}
