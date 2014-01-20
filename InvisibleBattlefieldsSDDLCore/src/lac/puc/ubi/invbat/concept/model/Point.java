package lac.puc.ubi.invbat.concept.model;

public class Point {
	private double lat;
	private double lng;
	private double x;
	private double y;
	private double lonAnchor = 1;
	private double latAnchor = 1;
	
	public Point(double _lat, double _lng) {
		lonAnchor = latAnchor = 1;
		setLatLng(_lat, _lng);
	}
	public void setLatLng(double _lat, double _lng) {
		lat = _lat;
		lng = _lng;
		x = (lng-lonAnchor)*( Math.toRadians( 6378137 ) )*Math.cos( latAnchor );
		y = (lat-latAnchor)*( Math.toRadians( 6378137 ) );
	}
	public void setXY(double _x, double _y) {
		x = _x;
		y = _y;
		lat = latAnchor + y/Math.toRadians( 6378137 );
		lng = lonAnchor + x/(( Math.toRadians( 6378137 ) )*Math.cos( latAnchor ));
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getLonAnchor() {
		return lonAnchor;
	}
	public void setLonAnchor(double lonAnchor) {
		this.lonAnchor = lonAnchor;
	}
	public double getLatAnchor() {
		return latAnchor;
	}
	public void setLatAnchor(double latAnchor) {
		this.latAnchor = latAnchor;
	}
	
	
	
}
