package br.pucrio.inf.lac.invisiblebattler.model;

import java.util.Date;

import br.pucrio.inf.lac.invisiblebattler.dao.BattleDAO;
import br.pucrio.inf.lac.invisiblebattler.dao.UserDAO;

public class UserBattle {
	private int id;
	private Battle battle;
	private User userFrom;
	private User userTo;
	private Date date;
	private int exp_points;
	private double sten;
	private double intel;
	private double agili;
	private boolean recived;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public void setBattle(int Battle_id) {
		BattleDAO daoBattle = new BattleDAO();
		this.battle = daoBattle.buscar(Battle_id);
	}
	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}
	public void setUserFrom(int userFrom) {
		UserDAO dao = new UserDAO();
		this.userFrom = dao.buscar(userFrom);
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public void setUserTo(int userTo) {
		UserDAO dao = new UserDAO();
		this.userTo = dao.buscar(userTo);
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

	public boolean isRecived() {
		return recived;
	}

	public void setRecived(boolean recived) {
		this.recived = recived;
	}

	public String toString() {
		String str = "UserBattle \nBattler: " + battle.toString()
				+ "\nUserFrom: " + userFrom.toString() + "\nUserTo: "
				+ userTo.toString() + "\ndate: " + date.toString()
				+ "\nexp_points: " + exp_points + "\n---------";
		return str;
	}
}
