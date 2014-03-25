import java.io.*;
import java.net.*;

public class Server extends Thread{
	
	//Globals
	protected DatagramSocket SOCK = null;
	public int QuoteCount = 0;
	public int Time_Interval = 3000;
	
	public static void main(String[] args) throws IOException{
		Server SERVER = new Server();
		SERVER.start();
	}
	
	public Server()throws IOException{
		SOCK = new DatagramSocket(444);
		System.out.println("Lauched Multicast server Thread");
		System.out.println("Wating for client to connect");
	}
	
	public void run(){
		
		while(true){
			
			try{
				byte[] buffer = new byte[256];
				String Message = "O computador fixo diz olá.";
				System.out.println(Message);
				buffer = Message.getBytes();
				
				InetAddress IP_ADDRESS_GROUP =InetAddress.getByName("224.0.0.1");
				DatagramPacket PACKET = new DatagramPacket(buffer,buffer.length,IP_ADDRESS_GROUP,444);
				
				SOCK.send(PACKET);
				
				try{
					sleep(Time_Interval/3);
				}
				catch(InterruptedException X){X.printStackTrace();}
			}
			catch(IOException X){
				X.printStackTrace();
				break;
			}
		}
		
		SOCK.close();
	}
	
	
	
	
	
}
