package location;

public interface ILocation {

	public long getTime();

	public double getLat();

	public double getLon();

	public double getAlt();

	public double getSpeed();

	public double getCourse();

	public double getAccuracy();

	public double getImei();

	public void setImei(double imei);

	public void setAccuracy(double accuracy);

	public void setCourse(double course);

	public void setSpeed(double speed);

	public void setAlt(double alt);

	public void setLon(double lon);

	public void setLat(double lat);

	public void setTime(long time);
}