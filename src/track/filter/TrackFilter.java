package track.filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import location.ILocation;

public class TrackFilter {
	private List<ITrackPointFilter> filters = new LinkedList<ITrackPointFilter>();

	public void addFilter(ITrackPointFilter filter) {
		filters.add(filter);
	}
	
	public List<ILocation> filter(List<ILocation> track) {
		
//		long logStartTime = track.get(0).getTime();
		
		
		List<ILocation> result = new ArrayList<ILocation>();
		TrackPoint tp = new TrackPoint();
				
		for(ILocation loc : track) {
			tp.setFromLocation(loc);
			
			boolean valid = true;
			for (ITrackPointFilter f : filters) {
				if (!f.apply(tp)) {
					valid = false;
					break;
				}				
			}
			if (!valid) {
				continue;
			}
			
			result.add(tp.getLocation());
		}

		return result;
	}
}
