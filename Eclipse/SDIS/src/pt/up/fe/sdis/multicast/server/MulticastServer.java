/*
 * @(#)MulticastServer.java  1.0  2012-03-11
 *
 * Copyright (c) 2012 by Rui Maranhao
 * All rights reserved.
 *
 */
package pt.up.fe.sdis.multicast.server;

import server.*;

public class MulticastServer { 
		
	public static void main(String args[]) throws Exception { 
		new ServiceAds().start();
		new Service().start();
	} 
} 


