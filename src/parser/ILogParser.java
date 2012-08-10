package parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ILogParser {
	// public void extractMessagesToFile(File f) throws IOException;
	public List<ILocation> parse(InputStream is) throws IOException;
}
