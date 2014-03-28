package FileManage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Misc.UtilFunc;

public class FileBase {
	
	protected String name;
	protected byte[] ID;
	protected int fileSize;
	private String version = "1.0";

	public FileBase(String fileName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		name = fileName;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(name.getBytes("ASCII"));

		ID = md.digest();
	}
	
	public byte[] getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public void print() throws UnsupportedEncodingException {
		String _size = Integer.toString(fileSize) + " Bytes";
		if (fileSize > 1000)
			_size = Integer.toString(fileSize / 1000) + " KB";
		System.out.println("File Name: " + name);
		System.out.println("Size: " + _size);
		System.out.println("ID: " + UtilFunc.byteToString(ID));
	}
}
