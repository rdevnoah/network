package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	public static String SERVER_IP = "192.168.1.12";
	public static int SERVER_PORT = 7000;
	
	public static void main(String[] args) {
		
		
		DatagramSocket socket = null;
		Scanner sc = null;
		try {
			// 1. Scanner 생성(표준입력 연결()
			sc = new Scanner(System.in);
			// 2. 소켓 생성
			socket = new DatagramSocket();
			
			// 3. 서버 연결 (UDP는 필요없음)
			//socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			//log("[client] connected");
			
			// 4. IOStream 받아오기 (UDP는 필요없음)
			//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			//PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			
			while(true) {
				//5. 키보드 입력 받기
				System.out.print(">>");
				String line = sc.nextLine();
				if ("quit".equals(line)) {
					break;
				}
				
				//6. 데이터 전송
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
						new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT));
				socket.send(sendPacket);
				
				
				//7. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE], UDPEchoServer.BUFFER_SIZE);
				socket.receive(receivePacket);
				
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "utf-8");
				//8. 콘솔 출력
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
