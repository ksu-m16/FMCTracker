package LogsReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SourceLogsListConstructor {
	public static List<File> getListOfLogs(String curDir) {
		System.out.println(curDir);
			File curDirFile = new File(curDir);
			String[] list = curDirFile.list();
			
			List<File> listOfLogs = new ArrayList<File>();
			for(String str : list) {
				File curElt = new File(curDir + File.separator + str);
				
				if(curElt.isDirectory()){
					getListOfLogs(curElt.getAbsolutePath());
				}
				if(str.endsWith(".txt")) {
					listOfLogs.add(curElt);	
					System.out.println(curElt.getAbsolutePath());
				}
			}
			return listOfLogs;
	}
}
