package lac.puc.ubi.invbat.concept.app;

import lac.puc.ubi.invbat.concept.activities.MainMenuScreen;
import lac.puc.ubi.invbat.concept.misc.BattleManager;
import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

/* Like a Registry game class */
public class InvBatApplication extends Application {

	public CharacterData m_player;
	public BattleManager m_battleManager = new BattleManager();
	static private Handler m_messageHandler = new Handler(){
		
	};

	public void handleBattleData(BattleData battle) {
		m_battleManager.newPendingBattle(battle);
	}

	public void handleLoginAuthorization(boolean answer, CharacterData charData)
	{
		if(answer)  //successfull login
		{
			handleCharData(charData);
		    Intent i = new Intent(getBaseContext(), MainMenuScreen.class);
			startActivity(i);
		}
		else 		//login failed
		{
			Toast.makeText(getBaseContext(), "Can't Login/Register right now!", Toast.LENGTH_LONG).show();
		}
	}
	
	private void handleCharData(CharacterData charData) {

		if(charData == null) //debug
			m_player = new CharacterData("Tobias the Great", 0);
		else
			m_player = charData;
	}

	public Handler getMessageHandler() {
		return m_messageHandler ;
	}
}
