package parser;

public class LocationFormatter {
	public static String format(ILocation l) {
		StringBuilder sb = new StringBuilder();
		sb.append("<gps i=" + l.getImei() + " t=" + l.getTime() + " x="
				+ l.getLon() + " y=" + l.getLat() + " z=" + l.getAlt() + " s="
				+ l.getSpeed() + " c=" + l.getCourse() + " a="
				+ l.getAccuracy() + ">");

		// String lString = "<gps i=" + l.getImei() + " t=" + l.getTime() +
		// " x=" + l.getLon() + " y=" + l.getLat() + " z=" + l.getAlt() +
		// " s=" + l.getSpeed() + " c=" + l.getCourse() +
		// " a=" + l.getAccuracy();
		// <gps i=356708044299665 t=1344422527990 x=30.25829429 y=59.94149353
		// z=32.9 s=0 c=0 a=204>

		return sb.toString();
	}
}
