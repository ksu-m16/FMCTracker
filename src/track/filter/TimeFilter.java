package track.filter;

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

	public static ITrackPointFilter getInstance(Map<String, String> params) {
		String startTime = String.valueOf(System.currentTimeMillis() / 1000 - 3600*24);
		if(params.size() != 1) {
			throw new IllegalArgumentException("You should specify only one parameter: " +
					"startTime=<startTime>. ");
		}

		if(params.containsKey("start")){
			startTime = params.get("start");
			if (startTime == null) {
				throw new IllegalArgumentException("empty start time value");
			}		
			try{
				Long.getLong(startTime);
			}
			catch (NumberFormatException ex) {
				throw new IllegalArgumentException("Start time should be an int number!");
			}
		}
		long ltime = Long.getLong(startTime);
//		TimeFilter tfilter = new TimeFilter(ltime - locs.get(0).getTime());
		TimeFilter tfilter = new TimeFilter(ltime);
		return tfilter;
	}
}
