/*
 * @(#)Service.java  1.0  2012-03-11
 *
 * Copyright (c) 2012 by Rui Maranhao
 * All rights reserved.
 *
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Service extends Thread {
	public static final int PORT = 1201; 

	public Service() throws IOException {
		super("Service");
	}
	
	public void run() { 
		try {
	        DatagramSocket serverSocket = new DatagramSocket(PORT);
	        
	        byte[] receiveData = new byte[1024];
	        byte[] sendData = new byte[1024];
	        
	        while(true) {
	              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	              serverSocket.receive(receivePacket);
	     
	              String sentence = new String(receivePacket.getData());
	              System.out.println("Request: " + sentence);
	              
	              InetAddress IPAddress = receivePacket.getAddress();
	              int port = receivePacket.getPort();
	              
	              String capitalizedSentence = sentence.toUpperCase() + "  [service performed @ " + (new Date()).toString() + "]";
	              sendData = capitalizedSentence.getBytes();
	              	              
	              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	              serverSocket.send(sendPacket);
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
