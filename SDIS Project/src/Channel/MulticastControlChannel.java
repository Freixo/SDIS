package Channel;

import java.io.IOException;
import java.net.DatagramPacket;
import Misc.UtilFunc;

public class MulticastControlChannel extends MulticastChannel{
	
	public MulticastControlChannel() throws IOException{
		super("224.0.0.1", 444);
	}
	public void run(){
		try{
			while(true){
				
				DatagramPacket PACKET;

				byte[] buff = new byte[256];
				PACKET = new DatagramPacket(buff,buff.length);
				SOCK.receive(PACKET);

				String[] Message = UtilFunc.byteSplit(PACKET.getData());
				System.out.print(PACKET.getAddress().toString().substring(1) + ": " + Message[6] + "\n");
			}
		}
		catch(IOException X){
			System.out.print(X);
		}

		SOCK.close();
	}
}
