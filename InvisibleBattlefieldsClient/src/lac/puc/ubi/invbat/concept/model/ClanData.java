package lac.puc.ubi.invbat.concept.model;

import java.io.Serializable;

public class ClanData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1316121559531451120L;
	private int id;
	private String name;

	public ClanData() {
		name = "";
	}

	public ClanData(String _name) {
		name = _name;
	}

	public ClanData(int _id, String _name) {
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
		String str = "Clan:\n id: " + id + "\nname: " + name + "\n---------";
		return str;
	}

}
