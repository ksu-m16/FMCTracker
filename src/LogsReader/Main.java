package LogsReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import location.ILocation;
import location.formatter.ILocationFormatter;
import location.formatter.LocationFormatterRegistry;
import message.sender.MessageSender;
import parser.ILogParser;
import parser.LogParserRegistry;


public class Main {

	public static void main(String[] args) throws IOException {

		// String sourceFolder =
		// "e:\\MyDocuments\\Eclipse\\FMCTracker\\tracker_logs";
		String sourceFolder = ".\\tracker_logs";
		
		
		List<File> listOfLogs = getListOfLogs(sourceFolder);

		for (File f : listOfLogs) {
			ILogParser extractor = LogParserRegistry.getInstanceByFileName(f);
			InputStream fi = new FileInputStream(f);

			ILocationFormatter formatter = LocationFormatterRegistry.getInstance("android");
			List<ILocation> locs = extractor.parse(fi);
			
			/*
			TrackFilter tf = new TrackFilter();
			tf.addFilter(new ImeiFilter("356708044299666"));
			tf.addFilter(new TimeFilter(1344643200000L - locs.get(0).getTime()));
			List<ILocation> mlocs = tf.filter(locs);
			*/					
			
			List<String> messages = new LinkedList<String>();
			
			// Send filtered data:
			/*
			for (ILocation loc : mlocs) {
				String message = formatter.format(loc);
				messages.add(message);
			}
			*/
			
			// Send raw data:
			for (ILocation loc : locs) {
			String message = formatter.format(loc);
			messages.add(message);
			}
			
			
			writeToFile(messages, f);
			
			MessageSender m = new MessageSender();
			System.out.println(m.send(messages));

		}

	}

	public static List<File> getListOfLogs(String curDir) {
		System.out.println(curDir);
		File curDirFile = new File(curDir);
		String[] list = curDirFile.list();

		List<File> listOfLogs = new ArrayList<File>();
		for (String str : list) {
			File curElt = new File(curDir + File.separator + str);

			if (curElt.isDirectory()) {
				getListOfLogs(curElt.getAbsolutePath());
			}
			if (str.endsWith(".txt")) {
				listOfLogs.add(curElt);
				System.out.println(curElt.getAbsolutePath());
			}
		}
		return listOfLogs;
	}

	public static void writeToFile(List<String> out, File sourceFile)
			throws IOException {
		String tmp = sourceFile.getAbsolutePath();
		tmp = tmp.substring(0, tmp.length() - 4);
		String outputLogName = tmp + ".csv";
		if ((new File(outputLogName)).exists()) {
			Calendar rightNow = Calendar.getInstance();
			outputLogName = tmp + rightNow.get(Calendar.YEAR)
					+ rightNow.get(Calendar.MONTH)
					+ rightNow.get(Calendar.DAY_OF_MONTH)
					+ rightNow.get(Calendar.HOUR)
					+ rightNow.get(Calendar.MINUTE) + ".csv";
		}

		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(
				outputLogName)));
		System.out.println("I write output to "
				+ (new File(outputLogName)).getAbsolutePath());
		
		for (String str : out) {
			out1.println(str);
		}
		out1.close();
	}
}
