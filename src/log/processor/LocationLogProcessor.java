package log.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.element.Element;

import parser.ILogParser;
import parser.LogParserRegistry;
import track.filter.ITrackPointFilter;
import track.filter.ImeiFilter;
import track.filter.TimeFilter;
import track.filter.TrackFilter;

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
	private TrackFilter filter;

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
	
	public boolean addFilter(ITrackPointFilter tf){
		filter.addFilter(tf);
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
	
	
	private Entry<String, String> parsePair(String pair) {		
		int pos = pair.indexOf("=");
		if (pos == -1) {			
			return new AbstractMap.SimpleEntry<String, String>(pair, null);			
		}
		String key = pair.substring(0, pos);
		String value = pair.substring(pos + 1);
		return new AbstractMap.SimpleEntry<String, String>(key, value);
	}
	
	private Map<String, String> parseParam(String param) {
		if (!param.startsWith("--")) {
			throw new IllegalArgumentException(
				"parameter should start with --, got '" + param + "'");
		}
		
		int pos = param.indexOf(",");
		if (pos == -1) {
			pos = param.length();
		}
		
		String main = param.substring(2, pos);		
		Map<String, String> args = new HashMap<String, String>();
		Entry<String, String> me = parsePair(main);
		args.put("_param", me.getKey());
		args.put("_value", me.getValue());

		if (pos >= param.length() - 1) {
			return args;
		}
		String tail = param.substring(pos + 1);
		
		String[] subArgs = tail.split(",");
		for (String arg : subArgs) {			
			Entry<String, String> e = parsePair(arg);
			args.put(e.getKey(), e.getValue());
		}
		
		return args;
	}
	
	private void throwIllegalArg(String name, String arg, String msg) {
		throw new IllegalArgumentException("invalid --" + name 
				+ " argument: '" + arg + "' : " + msg);
	}
	
	public void parseParams(String[] args){
		
//		args = new String[]{"--source=a/b/c", 
//				"--formatter=android", "--filter=imei,123", 
//				"--writer=net,host=1.2.3.4,port=1234"};
		
		for (String param: args) {
			Map<String, String> arg = parseParam(param);
			
			String main = arg.get("_param");
			String value = arg.get("_value");
			
			if (main.equals("source")) {				
				if (value == null) {
					throwIllegalArg("source", param, "source folder should be provided");
				}
				setSourceFolder(value);
				continue;
			}
			
			if (main.equals("formatter")) {				
				if (value == null) {
					throwIllegalArg("formatter", param, "formatter name should be provided");
				}
				setLocationFormatter(value);
				continue;
			}
			if (main.equals("writer")){				
				if (value == null) {
					throwIllegalArg("writer", param, "writer name should be provided");
				}								
				if (value.equals("file")){
					setLocationWriter(FileLocationWriter.getInstance(arg));
					continue;
				}
				if (value.equals("net")){
					setLocationWriter(NetLocationWriter.getInstance(arg));
					continue;
				}	
				throwIllegalArg("writer", param, "specified writer not available");				
			}
			
			if (main.equals("filter")){
				if (value == null) {
					throwIllegalArg("filter", param, "filter name should be provided");
				}								
				if (value.equals("imei")){
					addFilter(ImeiFilter.getInstance(arg));
					continue;
				}
				if (value.equals("time")){
					addFilter(TimeFilter.getInstance(arg));
					continue;
				}	
				throwIllegalArg("writer", param, "specified writer not available");		
			}
			
			throw new IllegalArgumentException("unknown argument '" + param + "'");											
		}		
	}
	
}
