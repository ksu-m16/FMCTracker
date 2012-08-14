package location_log_processor;

import java.io.IOException;

import location.formatter.ILocationFormatter;
import location.writer.ILocationWriter;

public interface ILocationLogProcessor {
	public boolean setSourceFolder(String sourceFolderPath);
	public boolean setLocationFormatter(ILocationFormatter formatter);
	public boolean setLocationWriter(ILocationWriter writer);
	public boolean run() throws IOException;
}
