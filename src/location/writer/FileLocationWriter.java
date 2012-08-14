package location.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import location.ILocation;

public class FileLocationWriter extends AbstractLocationWriter {
	private String targetFileName = "defaultOut.csv"; 
	private boolean append = true;
	private int counter = 0;
	
	public FileLocationWriter(String targetFileName, boolean append) {
		this.targetFileName = targetFileName;
		this.append = append;
	}
	
	public FileLocationWriter() {
		// default name: "defaultOut.csv"; append = true 
	}

	@Override
	public boolean write(List<ILocation> locs) throws IOException {
//		String tmp = sourceFile.getAbsolutePath();
//		tmp = tmp.substring(0, tmp.length() - 4);
//		String outputLogName = tmp + ".csv";
//		if ((new File(outputLogName)).exists()) {
//			Calendar rightNow = Calendar.getInstance();
//			outputLogName = tmp + rightNow.get(Calendar.YEAR)
//					+ rightNow.get(Calendar.MONTH)
//					+ rightNow.get(Calendar.DAY_OF_MONTH)
//					+ rightNow.get(Calendar.HOUR)
//					+ rightNow.get(Calendar.MINUTE) + ".csv";
//		}

	
//			if (targetFile.exists()) { 
		File targetFile = new File(targetFileName);
		
		if(!append &&(new File(targetFileName)).exists()) {
				String tmp = targetFileName;
				tmp = tmp.substring(0,tmp.length() - 4) + counter +".csv";
				targetFile = new File(tmp);
				counter++;
		}	

			
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(
				targetFile, append)));
		System.out.println("I write output to "
				+ targetFile.getAbsolutePath());
		
		for (ILocation l : locs) {
			out1.println(formatter.format(l));
		}
		out1.close();
		return true;
	}
	public boolean setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
		return true;
	}


}
