package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

public class FightResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UUID uuid;
	private UUID battleID;

	private Boolean battleAnswer;
	private int attackingClanID;
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public UUID getBattleID() {
		return battleID;
	}
	public void setBattleID(UUID battleID) {
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
}
