package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.ILocation;
import location.Location;

public class ExceptionLogParser extends AbstractLogParser {
	// private static Pattern p = Pattern.compile("Send: (<.+?>)");
	// [18]Send: <gps i=356708044299665 t=1344422531980 x=30.25829432
	// y=59.94149352 z=32.9 s=0 c=0 a=227>

	private static Pattern p = Pattern.compile("Send: (<gps i=(\\d+) t=(\\d+) "
			+ "x=([\\d\\.]+) y=([\\d\\.]+) z=([\\d\\.]+) s=([\\d\\.]+) "
			+ "c=([\\d\\.]+) a=([\\d\\.]+)>)");

	// @Override
	// public void extractMessagesToFile(File f) throws IOException {
	//
	// System.out.println("I am working with file " + f.getAbsolutePath());
	// BufferedReader reader = new BufferedReader(new InputStreamReader(new
	// FileInputStream(f)));
	// String line = "";
	// // LinkedList<String> listOfMessages = new LinkedList<String>();
	// List<ILocation> listOfMessages = new LinkedList<ILocation>();
	// while ((line = reader.readLine() ) != null) {
	// Matcher m = p.matcher(line);
	// while (m.find()) {
	// ILocation loc = new Location();
	// loc.setImei(Double.parseDouble(m.group(2)));
	// loc.setTime((long)Double.parseDouble(m.group(3)));
	// loc.setLon(Double.parseDouble(m.group(4)));
	// loc.setLat(Double.parseDouble(m.group(2)));
	// loc.setAlt(Double.parseDouble(m.group(2)));
	// loc.setSpeed(Double.parseDouble(m.group(2)));
	// loc.setCourse(Double.parseDouble(m.group(2)));
	// loc.setAccuracy(Double.parseDouble(m.group(2)));
	// listOfMessages.add(loc);
	// }
	//
	// }
	// reader.close();
	// System.out.println(listOfMessages.size() +
	// " messages extracted to file.");
	// writeListToFile(listOfMessages, f);
	// }

	@Override
	public List<ILocation> parse(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		// LinkedList<String> listOfMessages = new LinkedList<String>();
		List<ILocation> listOfMessages = new LinkedList<ILocation>();
		while ((line = reader.readLine()) != null) {
			Matcher m = p.matcher(line);
			while (m.find()) {
				ILocation loc = new Location();
				loc.setImei(Double.parseDouble(m.group(2)));
				loc.setTime((long) Double.parseDouble(m.group(3)));
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
