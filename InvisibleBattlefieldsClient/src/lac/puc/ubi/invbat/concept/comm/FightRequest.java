package lac.puc.ubi.invbat.concept.comm;

import java.io.Serializable;
import java.util.UUID;

import lac.puc.ubi.invbat.concept.model.BattleData;

public class FightRequest implements Serializable {
	
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID uuid;
	
	/** Battle Information for the Client **/
	private BattleData battle;

	public FightRequest(UUID _uuid, BattleData _battle)
	{
		uuid = _uuid;
		battle = _battle;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public BattleData getBattle() {
		return battle;
	}
	public void setBattle(BattleData battle) {
		this.battle = battle;
	}
}
