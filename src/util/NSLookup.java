package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class NSLookup {
	public static void main(String[] args) {
		String hostname = null;
		BufferedReader br = null;
		//Scanner sc = new Scanner(System.in);
		InetAddress[] addr;

		try {
			br = new BufferedReader(new InputStreamReader(System.in,"utf-8"));
			System.out.println("you joined nslookup service. escape to type 'exit'");
			while (true) {
				System.out.print("> "); 
				hostname = br.readLine();
				//hostname = sc.nextLine();
				if ("exit".equals(hostname)) {  
					br.close();
					break;
				}
				addr = InetAddress.getAllByName(hostname);
				for (InetAddress i : addr) {
					System.out.println(i.getHostName() + ":" + i.getHostAddress());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
