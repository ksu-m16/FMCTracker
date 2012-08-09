package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.LinkedList;

public abstract class AbstractLogParser implements ILogParser {	
	protected void writeListToFile(LinkedList<String> listOfMessages, File sourceFile) throws IOException {
		String tmp = sourceFile.getName();
		tmp = tmp.substring(0, tmp.length() - 4);
		String outputLogName = tmp + ".csv";
		if ((new File(outputLogName)).exists()){
			 Calendar rightNow = Calendar.getInstance();
			outputLogName = tmp + rightNow.get(Calendar.YEAR) + rightNow.get(Calendar.MONTH)
					+ rightNow.get(Calendar.DAY_OF_MONTH) + rightNow.get(Calendar.HOUR)
					+ rightNow.get(Calendar.MINUTE) + ".csv"; 
		}
		
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(outputLogName)));
		System.out.println("I wrire output to " + (new File(outputLogName)).getAbsolutePath());
		for(String s: listOfMessages) {
		out1.println(s);
		}
		out1.close();
	}
}
