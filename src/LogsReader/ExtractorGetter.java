package LogsReader;

import java.io.File;

public class ExtractorGetter {
	public static AbstractMessagesExtractor getExtractor(File f){
		String name = f.getName();
		if (name.substring(0,3).equals("GPS")){
			return new GpsLogMessagesExtractor();
		}
		return new ExceptionLogMessagesExtractor();
		
	}
}
