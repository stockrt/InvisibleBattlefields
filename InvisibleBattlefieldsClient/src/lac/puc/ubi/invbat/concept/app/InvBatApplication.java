package lac.puc.ubi.invbat.concept.app;

import lac.puc.ubi.invbat.concept.activities.MainMenuScreen;
import lac.puc.ubi.invbat.concept.misc.BattleManager;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.BattleResultData;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/* Like a Registry game class */
public class InvBatApplication extends Application {

	public CharacterData m_player;
	public BattleManager m_battleManager = new BattleManager();
	static private Handler m_messageHandler;

	public void handleBattleData(BattleData battle) {
		m_battleManager.newPendingBattle(battle);
	}

	public void handleLoginAuthorization(boolean answer, CharacterData charData)
	{
		if(answer)  //successfull login
		{
			handleCharData(charData);
		    Intent i = new Intent(getApplicationContext(), MainMenuScreen.class);
		    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
		else 		//login failed
		{
			Toast.makeText(getApplicationContext(), "Can't Login/Register right now!", Toast.LENGTH_LONG).show();
		}
	}
	
	private void handleCharData(CharacterData charData) {

		if(charData == null) //debug
			m_player = new CharacterData("Tobias the Great", 0);
		else
			m_player = charData;
	}

	public Handler getMessageHandler() 
	{
		if(m_messageHandler == null)
			initHandler();
		
		return m_messageHandler;
	}
	
	@SuppressLint("HandlerLeak")
	private void initHandler() 
	{
		m_messageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) 
		{
			super.handleMessage(msg);
			
			if (msg.getData().getString("status") != null) 
			{
				String status = msg.getData().getString("status");
				
				if (status.equals("connected")) {
					Toast.makeText(getApplicationContext(), "Conectado!", Toast.LENGTH_SHORT).show();
				}
				else if (status.equals("disconnected")) {
					Toast.makeText(getApplicationContext(), "Desconectado!", Toast.LENGTH_SHORT).show();
				}
				else if (status.equals("message")) {
					Toast.makeText(getApplicationContext(), msg.getData().getString("message"), Toast.LENGTH_LONG).show();
				}
			}
		}};
	}

	public void handleBattleResponse(BattleResultData battleResultData) {
		m_player = battleResultData.getCharFrom();
		
		Toast.makeText(getBaseContext(), "You got " + battleResultData.getExp_points() + " exp points!", Toast.LENGTH_SHORT).show();
	}
}
