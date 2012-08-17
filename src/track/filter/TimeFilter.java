package track.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TimeFilter implements ITrackPointFilter {	
	private long delta;
	private long startTime;

//	public TimeFilter(long delta) {
//		this.delta = delta;
//	}

	public TimeFilter(long startTime){
		this.startTime = startTime;
	}
		
	ITrackPointFilter startFilter = new ITrackPointFilter() {		
		@Override
		public boolean apply(TrackPoint p) {
			if (startTime != 0) {
				delta = startTime - p.time;
			}
			currentFilter = continueFilter;
			return continueFilter.apply(p);
		}
	};
	ITrackPointFilter continueFilter = new ITrackPointFilter() {			
		@Override
		public boolean apply(TrackPoint p) {
			p.time += delta;
			return true;
		}
	};
	ITrackPointFilter currentFilter = startFilter;
	
	@Override
	public boolean apply(TrackPoint p) {
		return currentFilter.apply(p);
	}

	public static ITrackPointFilter getInstanceFromParameters(Map<String, String> params) {
		if(! params.containsKey("start")){
			throw new IllegalArgumentException("You should specify start time parameter in time filter");		
		}
		String startTime = params.get("start");
		if (startTime == null) {
				throw new IllegalArgumentException("empty start time value");
		}	
		long ltime = 0;
//		long ltime = Long.getLong(startTime);
		
		if (Long.getLong(startTime) != null){
			ltime = Long.getLong(startTime);
		}
		if (Long.getLong(startTime) == null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = formatter.parse(startTime);
				ltime = date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Start time should be an int number!");
			}
		}
		
		TimeFilter tfilter = new TimeFilter(ltime);
		return tfilter;
	}
}
