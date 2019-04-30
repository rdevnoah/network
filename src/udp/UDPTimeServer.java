package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {
	public static final int PORT = 7000;
	public static final int BUFFER_SIZE = 256;
	
	public static void main(String[] args) {
		
		DatagramSocket socket = null;

		try {
			/*
			 * 수신받는 데이터에 상관없이 시간만 주구장창 보내주기
			 */
			socket = new DatagramSocket(PORT);
			
			while(true) {

				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);
				
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
				String date = format.format( new Date() );
				
				byte[] sendData = date.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
						receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
	
	

}
