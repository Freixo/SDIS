import java.io.*;
import java.net.*;
import javax.swing.*;

public class Peer extends Thread{

	public static JFrame MainWindow = new JFrame("Datagram Quote Server");
	public static JButton B_CONNECT = new JButton("Connect");
	public static JButton B_DISCONNECT = new JButton("Disconnect");
	public static JButton B_SEND = new JButton("Send");
	public static JScrollPane SP_OUTPUT = new JScrollPane();
	public static JScrollPane SP_INPUT = new JScrollPane();
	public static JTextArea TA_OUTPUT = new JTextArea();
	public static JTextArea TA_INPUT = new JTextArea();
	
	
	protected static MulticastSocket SOCK = null;
	protected static InetAddress IP_ADDRESS;


	//---------------------------------------------------------------------------------
	public Peer()throws IOException{
		SOCK = new MulticastSocket(444);
		
		BuildGui();
	}
	public static void main(String[] args)throws IOException{
		Peer CLIENT = new Peer();
		CLIENT.start();
	}
	//---------------------------------------------------------------------------------
	public void run(){
		
		try {
			TA_OUTPUT.append("My address: " + InetAddress.getLocalHost() + "\n\n");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		try{
			while(true){
				
				DatagramPacket PACKET;

				for(int i = 0; i < 1; i++){
					byte[] buff = new byte[256];
					PACKET = new DatagramPacket(buff,buff.length);
					SOCK.receive(PACKET);

					String Message = new String(PACKET.getData(), 0, PACKET.getLength());
					TA_OUTPUT.append(PACKET.getAddress().toString().substring(1) + ": " + Message + "\n");
				}
			}
		}
		catch(IOException X){
			System.out.print(X);
		}
		
		SOCK.close();
	}
	//---------------------------------------------------------------------------------
	public static void BuildGui(){
		MainWindow.setSize(490,425);
		MainWindow.setLocation(200,200);
		MainWindow.setResizable(false);
		MainWindow.setBackground(new java.awt.Color(0,0,255));
		MainWindow.getContentPane().setLayout(null);

		B_CONNECT.setBackground(new java.awt.Color(0,0,255));
		B_CONNECT.setForeground(new java.awt.Color(255,255,255));
		B_DISCONNECT.setBackground(new java.awt.Color(255,0,0));
		B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
		B_SEND.setBackground(new java.awt.Color(0,255,0));
		B_SEND.setForeground(new java.awt.Color(255,255,255));
		
		MainWindow.getContentPane().add(B_CONNECT);
		MainWindow.getContentPane().add(B_DISCONNECT);
		MainWindow.getContentPane().add(B_SEND);
		B_CONNECT.setBounds(130, 15, 110, 25);
		B_DISCONNECT.setBounds(250, 15, 110, 25);
		B_SEND.setBounds(400, 330, 70, 60);

		TA_OUTPUT.setLineWrap(true);
		TA_INPUT.setLineWrap(true);
		SP_OUTPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_OUTPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_OUTPUT.setViewportView(TA_OUTPUT);
		
		SP_INPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_INPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_INPUT.setViewportView(TA_INPUT);
		
		MainWindow.getContentPane().add(SP_OUTPUT);
		SP_OUTPUT.setBounds(10, 45, 460, 280);
		
		MainWindow.getContentPane().add(SP_INPUT);
		SP_INPUT.setBounds(10, 330, 380, 60);

		MainWindow_Action();

		MainWindow.setVisible(true);
	}
	public static void CONNECT(){
		try {
			IP_ADDRESS = InetAddress.getByName("224.0.0.1");
			SOCK.joinGroup(IP_ADDRESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void DISCONNECT(){
		try {
			SOCK.leaveGroup(IP_ADDRESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void MainWindow_Action(){
		B_CONNECT.addActionListener(

				new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						CONNECT();
					}
				}
				);
		B_DISCONNECT.addActionListener(

				new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						DISCONNECT();
					}
				}
				);
		B_SEND.addActionListener(

				new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						try{
							byte[] buffer = new byte[256];
							String Message = "O computador portatil diz olá.";
							System.out.println(Message);
							buffer = Message.getBytes();
							
							InetAddress IP_ADDRESS_GROUP =InetAddress.getByName("224.0.0.1");
							DatagramPacket PACKET2 = new DatagramPacket(buffer,buffer.length,IP_ADDRESS_GROUP,444);
							
							SOCK.send(PACKET2);
							
							try{
								sleep(1000);
							}
							catch(InterruptedException X){X.printStackTrace();}
						}
						catch(IOException X){
							X.printStackTrace();
						}
					}
				}
				);
	}
}

