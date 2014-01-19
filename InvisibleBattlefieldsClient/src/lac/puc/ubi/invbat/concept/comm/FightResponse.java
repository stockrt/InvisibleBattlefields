package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

public class FightResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** ID de usuário */
	private int userID;

	/** If the answer is true, the attacking clan ID */
	private int attackingClanID;
	
	/** Battle UUID for the Server */
	private int battleID;

	/** Answer of the Battle */
	private Boolean battleAnswer;
	
	public FightResponse(int _userID, int _attackingClanID, int _battleID, Boolean _battleAnswer)
	{
		this.userID = _userID;
		this.attackingClanID = _attackingClanID;
		this.battleID = _battleID;
		this.battleAnswer = _battleAnswer;
	}
	
	public int getBattleID() {
		return battleID;
	}
	public void setBattleID(int battleID) {
		this.battleID = battleID;
	}
	public Boolean getBattleAnswer() {
		return battleAnswer;
	}
	public void setBattleAnswer(Boolean battleAnswer) {
		this.battleAnswer = battleAnswer;
	}
	public int getAttackingClanID() {
		return attackingClanID;
	}
	public void setAttackingClanID(int attackingClanID) {
		this.attackingClanID = attackingClanID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
