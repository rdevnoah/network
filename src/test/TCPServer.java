package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			
			// 1-1. Time-Wait 시간에 소켓에 포트번호 할당을 가능하게 하기 위해
			serverSocket.setReuseAddress(true);
			
			
			// 2. 바인딩(binding)
			// : Socket에 SocketAddress(IPAddress + Port)를 바인딩 한다.
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhost = inetAddress.getHostAddress();
			System.out.println(localhost);
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// : 클라이언트의 연결요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			System.out.println("[server]connected by client [" + remoteHostAddress + ":" + remotePort + "]");

			try {
				// 4. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);
					if (readByteCount == -1) {
						// 클라이언트 정상종료 한 경우
						// close() 메소드 호출
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received : " + data);
					os.write(data.getBytes("utf-8"));
				}
			} catch (SocketException e) {
				System.out.println("[server] sudden closed by client");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
}
