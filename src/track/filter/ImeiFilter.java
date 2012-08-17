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
	
	public static ImeiFilter getInstanceFromParameters(Map<String, String> params) {			
		if(!params.containsKey("number")){
			throw new IllegalArgumentException("You should specify number parameter in IMEI filter");		
		}
		
		String newImei = params.get("number");
		if (newImei == null) {
			throw new IllegalArgumentException("empty imei value in IMEI filter");
		}			
				
		return new ImeiFilter(newImei);
	}
}









