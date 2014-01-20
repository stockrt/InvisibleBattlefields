package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import lac.puc.ubi.invbat.concept.dao.BattleDAO;
import lac.puc.ubi.invbat.concept.dao.CharacterDAO;
import br.pucrio.inf.lac.invisiblebattler.model.Battle;

public class BattleResultData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3390370579204061718L;

	private int id;

	private int battleId;
	private Battle battle;

	private int charFromId;
	private CharacterData charFrom;

	private int charToId;
	private CharacterData charTo;

	private Date date;

	private int exp_points;
	private double sten;
	private double intel;
	private double agili;
	private int state;

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

	public Battle getBattle() {
		if (battle == null) {
			BattleDAO daoBattle = new BattleDAO();
			this.battle = daoBattle.buscar(battleId);
		}
		return battle;
	}

	public void setBattle(Battle battle) {
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

	public void setCharacterDataTo(CharacterData userTo) {
		this.charTo = userTo;
	}

	public Date getDate() {
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
