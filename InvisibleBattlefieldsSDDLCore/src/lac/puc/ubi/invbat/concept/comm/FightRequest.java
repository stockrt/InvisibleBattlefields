package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import lac.puc.ubi.invbat.concept.model.BattleData;
import lac.puc.ubi.invbat.concept.model.CharacterData;

public class FightRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2760550871235150873L;
	private CharacterData charData;
	private BattleData battle;

	public FightRequest(BattleData _battle,CharacterData _charData)
	{
		battle = _battle;
		charData = _charData;
	}
	
	public BattleData getBattle() {
		return battle;
	}
	public void setBattle(BattleData battle) {
		this.battle = battle;
	}

	public CharacterData getCharData() {
		return charData;
	}

	public void setCharData(CharacterData charData) {
		this.charData = charData;
	}
		
	/**
	 * {@inheritDoc}
	 **/
	@Override
	public String toString() {

		JSONObject result = new JSONObject();

		try {

			if (charData != null) {
				result.put("chardata", charData.toString());
			} else {
				result.put("chardata", "null");
			}
			if (battle != null) {
				result.put("battle", battle.toString());
			} else {
				result.put("battle", "null");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
