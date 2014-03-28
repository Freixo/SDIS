package Channel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChannel extends Thread{
	
	protected MulticastSocket SOCK = null;
	protected InetAddress IP_ADDRESS;
	protected int PORT;
	
	public MulticastChannel(String ip_address, int port) throws IOException{
		PORT = port;
		
		SOCK = new MulticastSocket(PORT);
		IP_ADDRESS = InetAddress.getByName("224.0.0.1");
	}
	
	public void CONNECT(){
		try {
			SOCK.joinGroup(IP_ADDRESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("connected");
	}
	public void DISCONNECT(){
		try {
			SOCK.leaveGroup(IP_ADDRESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("disconnected");
	}
	public void run(){
	}
}
