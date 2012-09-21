package location.writer;

import location.formatter.AndroidFormatter;
import location.formatter.ILocationFormatter;
import location.formatter.LocationFormatterRegistry;

public abstract class AbstractLocationWriter implements ILocationWriter {
	protected ILocationFormatter formatter = new AndroidFormatter();
	public boolean setFormatter(String formatterName){
		formatter = LocationFormatterRegistry.getInstance(formatterName);
		return true;
	 }
	abstract public void setParamsFromString(String params);
}
