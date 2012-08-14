package location.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import location.ILocation;

public class NetLocationWriter extends AbstractLocationWriter {
	private String host = "50.19.246.128";
	private int port = 6565;
	
	public NetLocationWriter(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public NetLocationWriter() {
		// default host "50.19.246.128", port 6565
	}
	
	
	@Override
	public boolean write(List<ILocation> locs) throws IOException {
		Socket s;
		try {
			s = new Socket(host, port);
			System.out.println("socket ok");
		} catch (UnknownHostException ex) {
			System.out.println(ex.getMessage());
			return false;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}

		OutputStream os = s.getOutputStream();
		System.out.println("OutputStream ok");
		
		for (ILocation l : locs){
			os.write(formatter.format(l).getBytes());
		}

		try {
			s.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	public void setParameters(String host, int port){
		this.host = host;
		this.port = port;
	}
}
