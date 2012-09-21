package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import location.ILocation;
import location.formatter.ILocationFormatter;
import location.formatter.LocationFormatterRegistry;
import location.writer.FileLocationWriter;
import location.writer.ILocationWriter;
import log.processor.LocationLogProcessor;
import message.sender.MessageSender;
import parser.ILogParser;
import parser.LogParserRegistry;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame {
	public Main() {		
	}
	public static void main(String[] args) throws IOException {
		
/*		
 * How it should be done
 * и еще хочу автоопределение Unix time и даты в заданном формате.
 * 
 * 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = formatter.parse("2012-08-17 17:29:00");
			System.out.println(date.getTime() - System.currentTimeMillis());
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
				
		
//		args = new String[]{"--source=.\\tracker_logs", 
//				"--formatter=android", "--filter=imei,number=123", 
//				"--writer=file,mode=new,out=testt.csv",
//				"--filter=time,start=2012-08-17 16:00:00"};

//		args = new String[]{"--source=.\\tracker_logs", 
//				"--formatter=android", "--filter=imei,number=123", 
//				"--writer=file,mode=append,out=testt.csv"};
	
//		args = new String[]{"--writer=net"};
		
		
		LocationLogProcessor processor = new LocationLogProcessor();
		
		try {			
			processor.parseParams(args);
		} catch (IllegalArgumentException ex) {
			System.out.println("error parsing arguments");
			System.out.println(ex.getMessage());
			return;
		}
		
		if (!processor.isHelpMode()){
			try {
				processor.run();
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (NullPointerException e) {
				System.out.println(e.getMessage());
				processor.getHelp();
			}
		}
//			/*
//			TrackFilter tf = new TrackFilter();
//			tf.addFilter(new ImeiFilter("356708044299666"));
//			tf.addFilter(new TimeFilter(1344643200000L - locs.get(0).getTime()));
//			List<ILocation> mlocs = tf.filter(locs);
//			*/					
//			
////			List<String> messages = new LinkedList<String>();
//			
//			// Send filtered data:
//			/*
//			for (ILocation loc : mlocs) {
//				String message = formatter.format(loc);
//				messages.add(message);
//			}
//			*/
//			
//			// raw data:
////			for (ILocation loc : locs) {
////			String message = formatter.format(loc);
////			messages.add(message);
////			}
//			
//			
////			writeToFile(messages, f);
//			
////			MessageSender m = new MessageSender();
////			System.out.println(m.send(messages));
//
//		}
//
	}
}
