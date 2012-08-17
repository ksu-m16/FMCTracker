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
		String newImei = "356708044299665";
		if(params.size() != 1) {
			throw new IllegalArgumentException("You should specify only one parameter: " +
					"number=<imei>. ");
		}
		if(params.containsKey("number")){
			newImei = params.get("number");
			if (newImei == null) {
				throw new IllegalArgumentException("empty imei value");
			}		
			try{
				Long.getLong(newImei);
			}
			catch (NumberFormatException ex) {
				throw new IllegalArgumentException("Imei should be an int number!");
			}
		}
		ImeiFilter ifilter = new ImeiFilter(newImei);
		return ifilter;
	}
}









