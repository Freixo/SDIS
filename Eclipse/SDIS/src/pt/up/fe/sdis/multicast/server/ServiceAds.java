/*
 * @(#)ServiceAds.java  1.0  2012-03-11
 *
 * Copyright (c) 2012 by Rui Maranhao
 * All rights reserved.
 *
 */
package pt.up.fe.sdis.multicast.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class ServiceAds extends Thread {
	
	public final int PORT = 1200; 
	public final String adr = new String("230.0.0.1"); //any class D address

	public ServiceAds() throws IOException {
		super("ServiceAds");
	}
	
	public void run() { 		
		DatagramSocket socket; 
		DatagramPacket packet; 
		InetAddress address; 
			
		try {
			address = InetAddress.getByName(adr);
			socket = new DatagramSocket(); 

			byte[] data = null; 				
			while(true) { 
				Thread.sleep(1000); 
				
				String str = new String("PORT:1201");  
				data = str.getBytes(); 
				packet = new DatagramPacket(data,str.length(),address,PORT);
				System.out.println("Sending <" + str + ">"); 
				socket.send(packet);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	} 	
}