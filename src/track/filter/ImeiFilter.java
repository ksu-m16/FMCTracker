package track.filter;

import java.util.Map;
import java.util.Set;

import location.writer.FileLocationWriter;

public class ImeiFilter implements ITrackPointFilter {
	private String imei;
	public ImeiFilter(String imei) {
		this.imei = imei;
	}
	@Override
	public boolean apply(TrackPoint p) {
		p.imei = imei;
		return true;
	}	
	
	public static ImeiFilter getInstance(Map<String, String> params) {
		Set<Map.Entry<String, String>> paramsSet = params.entrySet();
		String newImei = "";
		if(paramsSet.size() != 1) {
			throw new IllegalArgumentException("you should specify only one imei value");
		}
		for (Map.Entry<String, String> entry : paramsSet){
			newImei = entry.getKey();
		}
		
		try{
		Integer.parseInt(newImei);
		}
		catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Imei should be an int number!");
		}
		ImeiFilter ifilter = new ImeiFilter(newImei);
		return ifilter;
	}
	
}
