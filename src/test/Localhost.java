package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {
	public static void main(String[] args) {

		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();

			/*
			 * InetAddress[] addr = InetAddress.getAllByName("localhost");
			 * 
			 * for (InetAddress i : addr) { System.out.println(i.getHostAddress()); }
			 */

			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			System.out.println(hostname + " : " + hostAddress);

			byte[] addresses = inetAddress.getAddress();
			for (byte address : addresses) {
				System.out.print(address & 0x000000ff);
				System.out.print(".");
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
