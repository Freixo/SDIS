package Misc;

import java.io.File;

public class UtilFunc {

	public static String byteToString(byte[] _byte) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < _byte.length; ++i)
			str.append(String.format("%02x", _byte[i]));
		return str.toString();
	}
	
	public static void createFolders()
	{
		File Dir_c = new File("Chunks");
		if (!Dir_c.exists())
			if (Dir_c.mkdir())
				System.out.println("Folder Chunks created");

		File Dir_s = new File("Share");
		if (!Dir_s.exists())
			if (Dir_s.mkdir())
				System.out.println("Folder Share created");

	}
	
	public static String[] byteSplit(byte[] _byte)
	{
		String str = byteToString(_byte);
		StringBuilder str_b = new StringBuilder();
		for(int i = 0 ; i < str.length() ; i += 2)
		{
			String value = str.substring(i, i+2);
			str_b.append((char)Integer.parseInt(value, 16));
		}
		return str_b.toString().split(" ");
	}
}
