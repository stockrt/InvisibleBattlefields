package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class FightResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int charId;
	private int battleId;

	private Boolean battleAnswer;
	/** If the answer is true, the attacking clan ID */
	private int attackingClanId;

	public int getCharId() {
		return charId;
	}

	public void setCharId(int userId) {
		this.charId = userId;
	}

	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	public Boolean getBattleAnswer() {
		return battleAnswer;
	}

	public void setBattleAnswer(Boolean battleAnswer) {
		this.battleAnswer = battleAnswer;
	}

	public int getAttackingClanId() {
		return attackingClanId;
	}

	public void setAttackingClanId(int attackingClanId) {
		this.attackingClanId = attackingClanId;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public String toString() {

		JSONObject result = new JSONObject();

		try {
			result.put("userId", charId);
			result.put("battleId", battleId);
			result.put("battleAnswer", battleAnswer);
			result.put("attackingClanId", attackingClanId);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
