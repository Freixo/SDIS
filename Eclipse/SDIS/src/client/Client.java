/*
 * @(#)Client.java  1.0  2012-03-11
 *
 * Copyright (c) 2012 by Rui Maranhao
 * All rights reserved.
 *
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	private int port = 1201;
	
	public Client(int port) {
		this.port = port; 
	}
	
	public void sendRequest() throws IOException {
		System.out.print("Write sentence:");
	    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	    String sentence = inFromUser.readLine();

	    DatagramSocket clientSocket = new DatagramSocket();
	    
	    InetAddress IPAddress = InetAddress.getByName("localhost");
	    
	    byte[] sendData = new byte[1024];
	    byte[] receiveData = new byte[2048];
	    
	    sendData = sentence.getBytes();
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	    clientSocket.send(sendPacket);
	    
	    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	    clientSocket.receive(receivePacket);
	    
	    String modifiedSentence = new String(receivePacket.getData());
	    System.out.println("Reply:" + modifiedSentence);
	    clientSocket.close();	
	}
}
