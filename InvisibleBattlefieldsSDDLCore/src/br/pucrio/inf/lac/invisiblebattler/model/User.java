package br.pucrio.inf.lac.invisiblebattler.model;

import java.io.Serializable;

import br.pucrio.inf.lac.invisiblebattler.dao.ClanDAO;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6276363735045040778L;
	
	private int id;
	private Clan clan;
	private String email;
	private String password;
	private String name;
	private int num_victories;
	private int exp_points;
	private int level;
	private int base_stren;
	private int base_intel;
	private int base_agili;
	private double mod_stren;
	private double mod_intel;
	private double mod_agili;

	public User() {
		name = "";
	}

	public User(String _name) {
		name = _name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public void setClan(Integer clanId) {
		ClanDAO daoClan = new ClanDAO();
		Clan clan = daoClan.buscar(clanId);
		this.clan = clan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum_victories() {
		return num_victories;
	}

	public void setNum_victories(int num_victories) {
		this.num_victories = num_victories;
	}

	public int getExp_points() {
		return exp_points;
	}

	public void setExp_points(int exp_points) {
		this.exp_points = exp_points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBase_stren() {
		return base_stren;
	}

	public void setBase_stren(int base_stren) {
		this.base_stren = base_stren;
	}

	public int getBase_intel() {
		return base_intel;
	}

	public void setBase_intel(int base_intel) {
		this.base_intel = base_intel;
	}

	public int getBase_agili() {
		return base_agili;
	}

	public void setBase_agili(int base_agili) {
		this.base_agili = base_agili;
	}


	public double getMod_stren() {
		return mod_stren;
	}

	public void setMod_stren(double mod_stren) {
		this.mod_stren = mod_stren;
	}

	public double getMod_intel() {
		return mod_intel;
	}

	public void setMod_intel(double mod_intel) {
		this.mod_intel = mod_intel;
	}

	public double getMod_agili() {
		return mod_agili;
	}

	public void setMod_agili(double mod_agili) {
		this.mod_agili = mod_agili;
	}

	public String toString() {
		String str = "User \nid: " + id + "\nclan: " + clan.toString()
				+ "\nemail: " + email + "\npassword: " + password + "\nname: "
				+ name + "\nnum_victories: " + num_victories + "\nexp_points: "
				+ exp_points + "\nlevel: " + level + "\nbase_stren: "
				+ base_stren + "\nbase_intel: " + base_intel + "\nbase_agili"
				+ base_agili + "\nmod_stren: " + mod_stren + "\nmod_intel: "
				+ mod_intel + "\nmod_agili: " + mod_agili + "\n---------";
		return str;
	}
}
