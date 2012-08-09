package parser;

import java.io.File;
import java.io.IOException;

public interface ILogParser {
	public void extractMessagesToFile(File f) throws IOException;
}
