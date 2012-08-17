package track.filter;

import java.util.Map;
import java.util.Set;

public class TimeFilter implements ITrackPointFilter {
	long delta = 0;
	public TimeFilter(long delta) {
		this.delta = delta;
	}
	
	@Override
	public boolean apply(TrackPoint p) {
		p.time += delta;
		return true;
	}

	public static ITrackPointFilter getInstance(Map<String, String> params) {
		Set<Map.Entry<String, String>> paramsSet = params.entrySet();
		String startTime = "";
		if(paramsSet.size() != 1) {
			throw new IllegalArgumentException("you should specify only one start time value");
		}
		for (Map.Entry<String, String> entry : paramsSet){
			startTime = entry.getKey();
		}
		long ltime = Long.getLong(startTime);
		
		TimeFilter tfilter = new TimeFilter(ltime - locs.get(0).getTime());
		
		return tfilter;
	}
}
