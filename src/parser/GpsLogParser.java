package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.ILocation;
import location.Location;

public class GpsLogParser extends AbstractLogParser {
	private static Pattern p = Pattern
			.compile("(\"type\":\"gps\",\"imei\":\"(\\d+)\",\"time\":(\\d+),\"lon\":([\\d\\.]+),\"lat\":([\\d\\.]+),\"alt\":([\\d\\.]+),\"speed\":([\\d\\.]+),\"course\":([\\d\\.]+),\"accuracy\":([\\d\\.]+))");

	// [2012-08-08 08:54:20] :
	// {"type":"gps","imei":"356708044299665","time":1344401588000,
	// "lon":30.42317018,"lat":59.89648519,"alt":26.8,"speed":0,"course":0,"accuracy":4}

	@Override
	public List<ILocation> parse(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		// LinkedList<String> listOfMessages = new LinkedList<String>();
		List<ILocation> listOfMessages = new LinkedList<ILocation>();
		while ((line = reader.readLine()) != null) {
			Matcher m = p.matcher(line);
			while (m.find()) {
				ILocation loc = new Location();
				loc.setImei(m.group(2));
				loc.setTime((long)Double.parseDouble(m.group(3)));
				loc.setLon(Double.parseDouble(m.group(4)));
				loc.setLat(Double.parseDouble(m.group(5)));
				loc.setAlt(Double.parseDouble(m.group(6)));
				loc.setSpeed(Double.parseDouble(m.group(7)));
				loc.setCourse(Double.parseDouble(m.group(8)));
				loc.setAccuracy(Double.parseDouble(m.group(9)));
				listOfMessages.add(loc);
			}

		}
		return listOfMessages;
	}
}
