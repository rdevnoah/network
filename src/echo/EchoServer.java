package echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private static final int PORT = 5000;

	public static void main(String[] args) {

		new EchoServer().go();
	}
	
	public void go() {
		
		ServerSocket serverSocket = null;
		try { // 서버 소켓 처리용 try
			serverSocket = new ServerSocket();
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhost = inetAddress.getHostAddress();
			System.out.println(localhost);
			
			// binding
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("server start...[port:" + PORT + "]");
			
			while (true) {
				Socket socket = serverSocket.accept(); // blocking
				
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void server(Socket socket) {

	}

	public static void log(String log) {
		System.out.println("[server#" + Thread.currentThread().getId() + "] " + log);
	}
}
