package MessageManage;

import java.util.Vector;

import FileManage.Chunk;
import FileManage.SmashedFile;
import Misc.UtilFunc;

public class Message {
	private Vector<byte[]> messages = new Vector<byte[]>();
	static private String CRLF = "0xDA";
	
	//Commands
	public static int PUTCHUNK = 1;
	public static int STORED = 2;
	public static int GETCHUNK = 3;
	public static int CHUNK = 4;
	public static int DELETE = 5;
	public static int REMOVED = 6;

	public void createSendMessage(int cmd, SmashedFile myFile) {
		switch (cmd) {
		case 1:
			createPUTCHUNK(myFile);
			break;

		case 2:
			createSTORED(myFile);
			break;

		case 3:
			createGETCHUNK(myFile);
			break;

		case 4:
			createCHUNK(myFile);
			break;

		case 5:
			createDELETE(myFile);
			break;

		case 6:
			createREMOVED(myFile);
			break;

		default:
			System.out.println("O comando " + cmd + " não é conhecido.");
			break;
		}
	}
	
	public Vector<byte[]> getMessages(){
		return messages;
	}
	
	private void createPUTCHUNK(SmashedFile myFile) {
		String header;
		header = "PUTCHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunks();

		int ReplicationDeg = 1;

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + ReplicationDeg
					+ " " + CRLF + "" + CRLF + " "
					+ fileChunks.get(i).getBytes() + " ";
			messages.add(message.getBytes());
		}
	}
	
	private void createSTORED(SmashedFile myFile) {
		String header;
		header = "STORED " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunks();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}
	}
	
	
	private void createREMOVED(SmashedFile myFile) {
		String header;
		header = "REMOVED " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunks();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}
	}

	private void createDELETE(SmashedFile myFile) {
		String message;
		message = "DELETE " + UtilFunc.byteToString(myFile.getID()) + " "
				+ CRLF + " " + CRLF + " ";

		messages.add(message.getBytes());
	}

	private void createCHUNK(SmashedFile myFile) {
		String header;
		header = "CHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunks();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " " + fileChunks.get(i).getBytes() + " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}

	}

	private void createGETCHUNK(SmashedFile myFile) {
		String header;
		header = "GETCHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunks();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}

	}
}
