package Channel;

import java.io.IOException;
import java.net.DatagramPacket;

import FileManage.Chunk;
import FileManage.SmashedFile;
import MessageManage.Message;
import Misc.UtilFunc;

public class MulticastDataBackup extends MulticastChannel{

	private SmashedFile currentFile = null;

	public MulticastDataBackup() throws IOException {
		super("224.0.0.1", 222);
	}

	public void run(){
		try{
			while(true){
				
				DatagramPacket PACKET;

				byte[] buff = new byte[256];
				PACKET = new DatagramPacket(buff,buff.length);
				SOCK.receive(PACKET);

				String[] Message = UtilFunc.byteSplit(PACKET.getData());
				
				if(Message[0].equals("PUTCHUNK")){
					System.out.print(PACKET.getAddress().toString().substring(1) + ": " + Message[0] + "\n");
					new Chunk(Integer.parseInt(Message[3]),Message[7].getBytes()).saveChunk(Message[2]);
				}
			}
		}
		catch(IOException X){
			System.out.print(X);
		}

		SOCK.close();
	}
	
	public void backup(){

		Message message = new Message();
		message.createSendMessage(Message.PUTCHUNK, currentFile);
		
		try {
			currentFile.saveChunks();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for(int i=0;i < message.getMessages().size(); i++){

			final byte[] str = message.getMessages().get(i);
			(new Thread() {
				public void run() {
					DatagramPacket PACKET2 = new DatagramPacket(str,str.length,IP_ADDRESS, PORT);

					try {
						SOCK.send(PACKET2);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();


		}	
	}
	public SmashedFile getCurrentFile(){
		return currentFile;
	}
	public void setCurrentFile(SmashedFile sm){
		currentFile = sm;
	}

}
