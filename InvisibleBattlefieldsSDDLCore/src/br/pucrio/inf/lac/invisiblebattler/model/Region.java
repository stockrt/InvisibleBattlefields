package br.pucrio.inf.lac.invisiblebattler.model;

import java.util.Vector;

public class Region {
	private int id;
	private String name;
	private String strPoints;
	private Vector<Point> points;

	public Region() {
		id = 0;
		name = strPoints = "";
		points = new Vector<Point>();
	}

	public Region(int _id, String _name, String _points) {
		id = _id;
		name = _name;
		setPoints(_points);
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

	public Vector<Point> getPoints() {
		return points;
	}

	public void setPoints(Vector<Point> points) {
		this.points = points;
	}

	public String getStrPoints() {
		return strPoints;
	}

	public void setStrPoints(String strPoints) {
		this.strPoints = strPoints;
	}

	public void setPoints(String _points) {
		strPoints = _points;
		String[] pp = _points.split("I");
		for (int i = 0; i < pp.length; i++) {
			String latlng = pp[i];
			String[] point = latlng.split(";");
			Double lat = Double.valueOf(point[0]);
			Double lng = Double.valueOf(point[1]);
			this.points.add(new Point(lat, lng));
		}
	}

	public String toString() {
		String str = "Region \n";
		str += "Name: " + name + "\n" + "Points: " + strPoints + "\n---------";
		return str;
	}

}
