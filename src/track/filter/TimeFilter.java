package track.filter;

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
}
