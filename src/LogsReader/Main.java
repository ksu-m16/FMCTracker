package LogsReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import parser.AbstractLogParser;
import parser.ILogParser;
import parser.LogParserRegistry;

public class Main {

	public static void main(String[] args) throws IOException  {
		
		String sourceFolder = "e:\\MyDocuments\\Eclipse\\FMCTracker\\tracker_logs";

		List<File> listOfLogs = SourceLogsListConstructor.getListOfLogs(sourceFolder);

		
		for(File f : listOfLogs ) {
			ILogParser extractor = LogParserRegistry.getInstanceByFileName(f);
			extractor.extractMessagesToFile(f);			
		}
	
	}

	}


