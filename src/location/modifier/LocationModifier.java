package location.modifier;

import java.util.LinkedList;
import java.util.List;

import location.ILocation;

public class LocationModifier {
	public static List<ILocation> setStartTime(long newStartTime, List<ILocation> loc){
		long startTime = loc.get(0).getTime(); 
		long delta = newStartTime - startTime; // seconds
		List<ILocation> modifiedLoc = new LinkedList<ILocation>();
		for (ILocation l : loc){
			ILocation lm = l.clone();
			lm.setTime(l.getTime() + delta);
			modifiedLoc.add(lm);
		}
		return modifiedLoc;
	}
	public static List<ILocation> setImei(String imei, List<ILocation> loc){
		List<ILocation> modifiedLoc = new LinkedList<ILocation>();
		for (ILocation l : loc){
			ILocation lm = l.clone();
			lm.setImei(imei);
			modifiedLoc.add(lm);
		}
		return modifiedLoc;
	}
}
