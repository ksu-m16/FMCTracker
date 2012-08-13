package track.filter;

import location.ILocation;
import location.Location;

public class TrackPoint {
	public String imei;
	public long time;
	public double lat;
	public double lon;
	public double alt;
	public double speed;
	public double course;
	public double accuracy;
	
	public TrackPoint() {		
	}
	
	public TrackPoint(ILocation loc) {
		setFromLocation(loc);
	}
	
	public void setFromLocation(ILocation loc) {
		this.imei = loc.getImei();
		this.time = loc.getTime();
		this.lat = loc.getLat();
		this.lon = loc.getLon();
		this.alt = loc.getAlt();
		this.speed = loc.getSpeed();
		this.course = loc.getCourse();
		this.accuracy = loc.getAccuracy();	
	}
	
	public ILocation getLocation() {
		return new Location(imei, time, 
			lat, lon, alt, 
		    speed, course, accuracy);		
	}	
}
