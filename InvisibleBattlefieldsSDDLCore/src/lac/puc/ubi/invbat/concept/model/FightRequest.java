package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;

import br.pucrio.inf.lac.invisiblebattler.model.User;

public class FightRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private User uuid;
	private BattleData battle;

	public FightRequest(User _uuid, BattleData _battle)
	{
		uuid = _uuid;
		battle = _battle;
	}
	
	public User getUuid() {
		return uuid;
	}
	public void setUuid(User uuid) {
		this.uuid = uuid;
	}
	public BattleData getBattle() {
		return battle;
	}
	public void setBattle(BattleData battle) {
		this.battle = battle;
	}
	
	
}
