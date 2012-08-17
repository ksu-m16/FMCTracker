package location.formatter;

import java.util.ArrayList;
import java.util.List;

import parser.ExceptionLogParser;
import parser.GpsLogParser;

public class LocationFormatterRegistry {
	public static List<ILocationFormatter> formatters = new ArrayList<ILocationFormatter>();
	public static ILocationFormatter getAndroidInstance() {
		return AndroidFormatter.getInstance();		
	}
	
	public static void registerFormatter(ILocationFormatter f){
		formatters.add(f);
	}	
	
	public static ILocationFormatter getInstance(String formatterName){
		for (ILocationFormatter f: formatters){
			if (f.getName().equals(formatterName)){
				return f;
			}
		}
	
//		if (formatterName.equals("android")) {
//			return new AndroidFormatter();
//		}
		return null;
	}
	public static List<String> getFormatterNames(){
		List<String> names = new ArrayList<String>();
		for(ILocationFormatter f: formatters){
			names.add(f.getName());
		}
		return names;
	}
	
	static {
		registerFormatter(AndroidFormatter.getInstance());
	}
}

