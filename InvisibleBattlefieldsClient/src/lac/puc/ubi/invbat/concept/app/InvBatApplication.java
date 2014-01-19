package lac.puc.ubi.invbat.concept.app;

import lac.puc.ubi.invbat.concept.misc.BattleManager;
import lac.puc.ubi.invbat.concept.model.CharacterData;
import android.app.Application;

/* Like a Registry game class */
public class InvBatApplication extends Application {

	public CharacterData m_player;
	public BattleManager m_battleManager = new BattleManager();
	
	/* Temp */
	public void validateCharData() {
		//TODO: faz request da character data
		
		m_player = new CharacterData("Tobias the Great", 0);
	}
}
