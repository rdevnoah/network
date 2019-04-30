package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPTimeClient {
	public static String SERVER_IP = "192.168.1.12";
	public static int SERVER_PORT = 7000;
	
	public static void main(String[] args) {
		
		
		DatagramSocket socket = null;
		Scanner sc = null;
		try {
			
			sc = new Scanner(System.in);
			
			socket = new DatagramSocket();
			
			
			
			while(true) {
				System.out.print(">>");
				String line = sc.nextLine();
				if ("quit".equals(line)) {
					break;
				}
				
			
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
						new InetSocketAddress(SERVER_IP, UDPTimeServer.PORT));
				socket.send(sendPacket);
				
				
			
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPTimeServer.BUFFER_SIZE], UDPTimeServer.BUFFER_SIZE);
				socket.receive(receivePacket);
				
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "utf-8");
			
				System.out.println("<<" + message);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}

		}
	}

	public static void log(String data) {
		System.out.println("[client]"+data);
	}

}
