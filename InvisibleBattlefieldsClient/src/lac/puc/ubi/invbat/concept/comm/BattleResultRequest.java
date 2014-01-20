package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;

public class BattleResultRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int battleId;
	private int userId;
	
	public BattleResultRequest(int _battleId, int _userId)
	{
		battleId = _battleId;
		userId = _userId;
	}
	
	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
