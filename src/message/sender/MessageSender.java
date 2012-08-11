package message.sender;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class MessageSender {
//	private String host = "54.247.119.28";
	private String host = "50.19.246.128";
	private int port = 6565;

	public boolean send(List<String> messages) throws IOException {
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
		
		for (String str : messages) {
			os.write(str.getBytes());
			System.out.println("I send message " + str);
		}

		try {
			s.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
}
