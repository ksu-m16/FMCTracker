package parser;

import java.io.File;


public class LogParserRegistry {
	public static ILogParser getInstanceByFileName(File f){
		String name = f.getName();
		if (name.substring(0,3).equals("GPS")){
			return new GpsLogParser();
		}
		return new ExceptionLogParser();		
	}
}
