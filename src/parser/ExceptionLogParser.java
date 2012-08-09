package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionLogParser extends AbstractLogParser {
	private static Pattern p = Pattern.compile("Send: (<.+?>)");

	@Override
	public void extractMessagesToFile(File f) throws IOException {

		System.out.println("I am working with file " + f.getAbsolutePath());
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
 		String line = "";
		LinkedList<String> listOfMessages = new LinkedList<String>();      
 		while ((line = reader.readLine() ) != null) {
			Matcher m = p.matcher(line);
   				while (m.find()) {
   					listOfMessages.add(m.group(1));
				}

 		}
		reader.close();
		System.out.println(listOfMessages.size() + " messages extracted to file.");
		writeListToFile(listOfMessages, f);
	}
	

}
