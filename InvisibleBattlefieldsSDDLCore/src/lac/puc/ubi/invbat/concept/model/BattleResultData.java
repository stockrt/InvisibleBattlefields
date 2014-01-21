package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;

import lac.puc.ubi.invbat.concept.dao.BattleDAO;
import lac.puc.ubi.invbat.concept.dao.CharacterDAO;
import lac.puc.ubi.invbat.concept.dao.ClanDAO;

import org.json.JSONObject;

public class BattleResultData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3390370579204061718L;

	private int id;

	private int battleId;
	private BattleData battle;

	private int charFromId;
	private CharacterData charFrom;

	private int charToId;
	private CharacterData charTo;

	private int clanId;
	private ClanData clan;

	private Date date;

	private int exp_points;
	private double sten;
	private double intel;
	private double agili;
	private int state;

	public BattleResultData() {
		super();
		date = null;
		battle = null;
		charFrom = null;
		charTo = null;
		clan = null;
	}

	public BattleResultData(int battleId, int charFromId, int clanAttackId) {
		super();
		this.battleId = battleId;
		this.charFromId = charFromId;
		this.clanId = clanAttackId;
		date = null;
		battle = null;
		charFrom = null;
		charTo = null;
		clan = null;
	}

	public int getClanId() {
		return clanId;
	}

	public void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public ClanData getClan() {
		if (clan == null) {
			ClanDAO dao = new ClanDAO();
			clan = dao.buscar(clanId);
		}
		return clan;
	}

	public void setClan(ClanData clan) {
		this.clan = clan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	public BattleData getBattle() {
		if (battle == null) {
			BattleDAO daoBattle = new BattleDAO();
			this.battle = daoBattle.buscar(battleId);
		}
		return battle;
	}

	public void setBattle(BattleData battle) {
		this.battle = battle;
	}

	public int getCharFromId() {
		return charFromId;
	}

	public void setCharFromId(int charFromId) {
		this.charFromId = charFromId;
	}

	public CharacterData getCharFrom() {
		if (charFrom == null) {
			CharacterDAO dao = new CharacterDAO();
			this.charFrom = dao.buscar(charFromId);
		}
		return charFrom;
	}

	public void setCharFrom(CharacterData charFrom) {
		this.charFrom = charFrom;
	}

	public int getCharToId() {
		return charToId;
	}

	public void setCharToId(int charToId) {
		this.charToId = charToId;
	}

	public CharacterData getCharTo() {
		if (charTo == null) {
			CharacterDAO dao = new CharacterDAO();
			this.charTo = dao.buscar(charToId);
		}
		return charTo;
	}

	public void setCharTo(CharacterData charTo) {
		this.charTo = charTo;
	}

	public Date getDate() {
		if (date == null) {
			date = getBattle().getDate();
		}			
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getExp_points() {
		return exp_points;
	}

	public void setExp_points(int exp_points) {
		this.exp_points = exp_points;
	}

	public double getSten() {
		return sten;
	}

	public void setSten(double sten) {
		this.sten = sten;
	}

	public double getIntel() {
		return intel;
	}

	public void setIntel(double intel) {
		this.intel = intel;
	}

	public double getAgili() {
		return agili;
	}

	public void setAgili(double agili) {
		this.agili = agili;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String toString() {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("battleId", battleId);
		result.put("charFromId", charFromId);
		result.put("charToId", charToId);
		result.put("date", date.toString());
		result.put("exp_points", exp_points);
		result.put("sten", sten);
		result.put("intel", intel);
		result.put("agili", agili);
		result.put("state", state);
		return result.toString();
	}
}
