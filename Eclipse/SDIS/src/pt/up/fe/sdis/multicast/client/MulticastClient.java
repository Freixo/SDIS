/*
 * @(#)MulticastClient.java  1.0  2012-03-11
 *
 * Copyright (c) 2012 by Rui Maranhao
 * All rights reserved.
 *
 */
package pt.up.fe.sdis.multicast.client;

import java.net.*;

import client.Client;

public class MulticastClient {
	
	public static final int PORT = 1200; 
	
	public static void main(String args[]) throws Exception{

		MulticastSocket socket;
		DatagramPacket packet;
		InetAddress address; 

		address = InetAddress.getByName("230.0.0.1");        
		socket = new MulticastSocket(PORT);

		//join a Multicast group and send the group salutations

		socket.joinGroup(address);
		byte[] data = new byte[512];
		packet = new DatagramPacket(data,data.length);
                             
		// receive the packets 
		socket.receive(packet); 
		String str = new String(packet.getData());
		System.out.println("Signal received from "+ packet.getAddress() + " is : " +str);
		
		// leave group
		socket.leaveGroup(address);
		socket.close();

		//Unicast, UDP request
		String port[] = str.split(":");				
		Client c = new Client(Integer.parseInt(port[1].trim()));
		c.sendRequest();
	}      
}  