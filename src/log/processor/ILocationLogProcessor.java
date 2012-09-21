package log.processor;

import java.io.IOException;

import location.formatter.ILocationFormatter;
import location.writer.ILocationWriter;

public interface ILocationLogProcessor {
	public void setSourceFolder(String sourceFolderPath);
	public void setLocationFormatter(ILocationFormatter formatter);
	public void setLocationWriter(ILocationWriter writer);
	public void run() throws IOException;
}
