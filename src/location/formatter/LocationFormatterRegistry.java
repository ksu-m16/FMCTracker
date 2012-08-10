package location.formatter;

public class LocationFormatterRegistry {
	public static ILocationFormatter getAndroidInstance() {
		return AndroidFormatter.getInstance();		
	}
	
	//public static registerFormatter(ILocationFormatter f);
	//public static ILocationFormatter getInstance(String formatterName);
	//public static List<String> getFormatterNames()
}
