package location.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static NetLocationWriter getInstance(Map<String, String> params) {
		NetLocationWriter nlw = new NetLocationWriter();
		nlw.setParamsFromMap(params);
		return nlw;
	}
	

	@Override
	public void write(List<ILocation> locs) throws IOException {
		Socket s;
		try {
			s = new Socket(host, port);
			System.out.println("socket ok: " + host + ", " + port);
		} catch (UnknownHostException ex) {
			System.out.println(ex.getMessage());
			return;

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return;
		}

		OutputStream os = s.getOutputStream();
//		System.out.println("OutputStream ok");

		for (ILocation l : locs) {
			os.write(formatter.format(l).getBytes());
		}

		try {
			s.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return;
		}

	}

	public void setParameters(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void setParamsFromString(String params) {
		// --writer=net,host=1.2.3.4,port=1234
		Pattern p = Pattern
				.compile("host\\s{0,100}=\\s{0,100}([\\d\\.]+?),\\s{0,100}port\\s{0,100}=\\s{0,100}(\\d+)");
		Matcher m = p.matcher(params);
		if (m.find()) {
			host = m.group(1);
			port = (int) Integer.valueOf(m.group(2));
		} else {
			System.out.println("Wrong parameters. Possible params are:\n"
					+ " host=<host>), port=<port>");
		}
	}
	
	public void setParamsFromMap(Map<String, String> params) {
		String host = params.get("host");
		String port = params.get("port");
		if (host == null) {
			throw new IllegalArgumentException(
				"host parameter is not set for net writer");
		}
		if (port == null) {
			throw new IllegalArgumentException(
				"port parameter is not set for net writer");			
		}
		
		try {
			this.host = host;		
			this.port = Integer.valueOf(port);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(
				"host parameter should be a number");
		}
	}
	
	public String getHost(){
		return host;
	}
	public int getPort(){
		return port;
	}
}
