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
import location.formatter.AndroidFormatter;


import parser.AbstractLogParser;
import parser.ILogParser;
import parser.LogParserRegistry;

public class Main {

	public static void main(String[] args) throws IOException {

		// String sourceFolder =
		// "e:\\MyDocuments\\Eclipse\\FMCTracker\\tracker_logs";
		String sourceFolder = "w:\\Testing\\Selenium_Java\\eclipse_workspace\\xFMCTracker\\tracker_logs";

		List<File> listOfLogs = getListOfLogs(sourceFolder);

		for (File f : listOfLogs) {
			ILogParser extractor = LogParserRegistry.getInstanceByFileName(f);
			// extractor.extractMessagesToFile(f);
			InputStream fi = new FileInputStream(f);

			writeToFile(extractor.parse(fi), f);

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

	public static void writeToFile(List<ILocation> outLog, File sourceFile)
			throws IOException {
		String tmp = sourceFile.getName();
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
		System.out.println("I wrire output to "
				+ (new File(outputLogName)).getAbsolutePath());
		for (ILocation loc : outLog) {
			out1.println(AndroidFormatter.format(loc));
		}
		out1.close();
	}
}
