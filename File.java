package FileManage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import Misc.UtilFunc;


public class File {
	private String name;
	private byte[] ID;
	private int size;
	private Vector<Chunk> chunks =  new Vector<Chunk>();
	private String version = "1.0";
	
	public File(String fileName) throws IOException, NoSuchAlgorithmException
	{
		String path = "Share/" + fileName;
		FileInputStream file = new FileInputStream(path);
		
		name = fileName;
		size = file.available();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(name.getBytes("ASCII"));

		ID = md.digest();
		
		int fileAvailable = file.available();
		for(int i = 0; fileAvailable > 0; ++i)
		{
			int byteSize;
			if(fileAvailable > 64000)
				byteSize = 64000;
			else
				byteSize = fileAvailable;
			Chunk ch = new Chunk(i,byteSize);
			chunks.add(ch);
			file.read(chunks.get(i).getChunk());
			fileAvailable = file.available();
		}
		
		file.close();
	}
	
	public void print() throws UnsupportedEncodingException
	{
		String _size = Integer.toString(size) + " Bytes";
		if(size > 1000)
			_size = Integer.toString(size / 1000) + " KB";
		System.out.println("File Name: " + name);
		System.out.println("Size: " + _size);
		System.out.println("ID: " + UtilFunc.byteToString(ID) + " " + ID.length);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public byte[] getID() {
		return ID;
	}

	public Vector<Chunk> getChunk() {
		return chunks;
		
	}
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		File myFile = new File("AC.jpg");
		myFile.print();
	}
}
