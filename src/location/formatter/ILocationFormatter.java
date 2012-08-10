package location.formatter;

import location.ILocation;

public interface ILocationFormatter {
	public String format(ILocation l);
	public String getName();
}
