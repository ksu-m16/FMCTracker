package track.filter;

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
}
