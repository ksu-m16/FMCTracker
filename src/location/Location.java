package location;

public class Location implements ILocation, Cloneable {
	private long time;
	private double lat;
	private double lon;
	private double alt;
	private double speed;
	private double course;
	private double accuracy;
	private String imei;
	
	public Location() {		
	}
	
	public Location(String imei, long time, 
		double lat, double lon, double alt,
		double speed, double course, double accuracy) {
		
		this.imei = imei;
		this.time = time;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.speed = speed;
		this.course = course;
		this.accuracy = accuracy;		
	}

	public ILocation clone(){
		ILocation newLoc = new Location();
		newLoc.setTime(time);
		newLoc.setLat(lat);
		newLoc.setLon(lon);
		newLoc.setAlt(alt);
		newLoc.setSpeed(speed);
		newLoc.setCourse(course);
		newLoc.setAccuracy(accuracy);
		newLoc.setImei(imei);
		return newLoc;
	}
	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return this.time;
	}

	@Override
	public double getLat() {
		// TODO Auto-generated method stub
		return this.lat;
	}

	@Override
	public double getLon() {
		// TODO Auto-generated method stub
		return this.lon;
	}

	@Override
	public double getAlt() {
		// TODO Auto-generated method stub
		return this.alt;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public double getCourse() {
		// TODO Auto-generated method stub
		return this.course;
	}

	@Override
	public double getAccuracy() {
		// TODO Auto-generated method stub
		return this.accuracy;
	}

	@Override
	public String getImei() {
		// TODO Auto-generated method stub
		return this.imei;
	}

	@Override
	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public void setAlt(double alt) {
		this.alt = alt;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void setCourse(double course) {
		this.course = course;
	}

	@Override
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public void setImei(String imei) {
		this.imei = imei;
	}

}
