package br.pucrio.inf.lac.invisiblebattler.model;

public class Clan {
	private int id;
	private String name;
	
	public Clan () {
		name = "";
	}
	public Clan (String _name) {
		name = _name;
	}
	public Clan (int _id, String _name) {
		id = _id;
		name = _name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		String str = "\nid: " + id +
				"\nname: "+name;
		return str;
	}
	
}
