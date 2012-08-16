package location_log_processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import parser.ILogParser;
import parser.LogParserRegistry;

import location.ILocation;
import location.formatter.ILocationFormatter;
import location.formatter.LocationFormatterRegistry;
import location.writer.FileLocationWriter;
import location.writer.ILocationWriter;
import location.writer.NetLocationWriter;

public class LocationLogProcessor implements ILocationLogProcessor {
	private ILocationWriter writer;
	private File sourceFolder;
	private String host;
	private int port;
	private ILocationFormatter formatter;

	@Override
	public boolean setSourceFolder(String sourceFolderPath) {
		sourceFolder = new File(sourceFolderPath);
		return true;
	}

	@Override
	public boolean setLocationFormatter(ILocationFormatter formatter) {
		this.formatter = formatter;
		return true;
	}

	public boolean setLocationFormatter(String formatterName) {
		formatter = LocationFormatterRegistry.getInstance(formatterName);
		return true;
	}

	@Override
	public boolean setLocationWriter(ILocationWriter writer) {
		this.writer = writer;
		return true;
	}

	public boolean setNetLocationWriter(String host, int port) {
		writer = new NetLocationWriter(host, port);
		return true;

	}

	public boolean setFileLocationWriter(String targetFileName, boolean append) {
		writer = new FileLocationWriter(targetFileName, append);
		return true;
	}

	private List<File> getListOfLogs(String curDir) {
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
	
	@Override
	public boolean run() throws IOException {
		List<File> listOfLogs = getListOfLogs(sourceFolder.getAbsolutePath());
		
		for (File f : listOfLogs) {
			ILogParser extractor = LogParserRegistry.getInstanceByFileName(f);
			InputStream fi = new FileInputStream(f);

			List<ILocation> locs = extractor.parse(fi);
			
			writer.write(locs);
			
			
			/*
			TrackFilter tf = new TrackFilter();
			tf.addFilter(new ImeiFilter("356708044299666"));
			tf.addFilter(new TimeFilter(1344643200000L - locs.get(0).getTime()));
			List<ILocation> mlocs = tf.filter(locs);
			*/					
			
//			List<String> messages = new LinkedList<String>();
			
			// Send filtered data:
			/*
			for (ILocation loc : mlocs) {
				String message = formatter.format(loc);
				messages.add(message);
			}
			*/
			
			// raw data:
//			for (ILocation loc : locs) {
//			String message = formatter.format(loc);
//			messages.add(message);
//			}
			
			
//			writeToFile(messages, f);
			
//			MessageSender m = new MessageSender();
//			System.out.println(m.send(messages));

	}
		return true;
	}
	public boolean parseParams(String[] args){
		
//		args = new String[]{"--source=a/b/c", 
//				"--formatter=android", "--filter=imei,123", 
//				"--writer=net,host=1.2.3.4,port=1234"};
		
		for (String param: args) {
			if (param.startsWith("--source")){
				setSourceFolder(param.substring(9, param.length()).trim());
				System.out.println("Soutce folder: " + param.substring(9, param.length()).trim());
				continue;
			}
			if (param.startsWith("--formatter")){
				setLocationFormatter(param.substring(12, param.length()).trim());
				System.out.println("Formatter: " + param.substring(12, param.length()).trim());
				continue;
			}
			if (param.startsWith("--writer")){
				if (param.substring(param.indexOf("=") + 1, param.indexOf(",")).equals("file")){
					FileLocationWriter writer = new FileLocationWriter();
					writer.setParamsFromString(param);
					System.out.println("FileLocationWriter: " + writer.getTargetFileName() + 
							", " + writer.getMode());
					setLocationWriter(writer);
					continue;
				}
				if (param.substring(param.indexOf("=") + 1, param.indexOf(",")).equals("net")){
					NetLocationWriter writer = new NetLocationWriter();
					writer.setParamsFromString(param);
					System.out.println("NetLocationWriter: " + writer.getHost() + 
							", " + writer.getPort());
					setLocationWriter(writer);
					continue;
				}
		
				
				else {
					System.out.println("Unknown writer. Available writers: net, file. \"" + param.substring(param.indexOf("=") + 1, param.indexOf(",")) + "\"");
					return false;
				}

			}
			if (param.startsWith("--filter")){
				// don't forget to write smthng this about	
				continue;
			}
			else {
				System.out.println("Unknown parameter " + param + ". Try again :)");
				return false;
			}
			
		}
		return true;
	}
	
}
