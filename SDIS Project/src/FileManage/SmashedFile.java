package FileManage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import Misc.UtilFunc;

public class SmashedFile extends FileBase{
	
	private Vector<Chunk> chunks = new Vector<Chunk>();
	private static int CHUNK_SIZE = 64000;

	public SmashedFile(String fileName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		super(fileName);
	}
	
	public Vector<Chunk> getChunks(){
		return chunks;
	}
	
	/**
	 * Abre o ficheiro e guarda os pedaços do mesmo num vector de pedaços.
	 * A leitura do ficheiro é feita de CHUNK_SIZE em CHUNK_SIZE.
	 * 
	 * @throws IOException
	 */
	public void loadFile() throws IOException {

		FileInputStream file = new FileInputStream(name);
		fileSize = file.available();

		int fileAvailable = file.available();
		int byteSize;
		
		for (int i = 0; fileAvailable > 0; ++i) {
			if (fileAvailable > CHUNK_SIZE)
				byteSize = CHUNK_SIZE;
			else
				byteSize = fileAvailable;
			
			Chunk ch = new Chunk(i, byteSize);
			chunks.add(ch);
			
			file.read(ch.getBytes());
			fileAvailable = file.available();
		}
		file.close();
	}

	public void loadChunks() throws IOException {

		FileInputStream info = new FileInputStream("Chunks/"
				+ UtilFunc.byteToString(ID) + ".txt");
		byte[] b = new byte[1];
		int nlines = 0;
		
		while (info.read(b) != -1)
			if (UtilFunc.byteToString(b).equals("0a"))
				break;
		
		while (info.read(b) != -1)
			if (UtilFunc.byteToString(b).equals("0a"))
				nlines++;
		fileSize = 0;

		for (int i = 0; i < nlines; ++i) {
			try {
				FileInputStream file = new FileInputStream("Chunks/" + UtilFunc.byteToString(ID) + "." + i);
				byte[] chunk = new byte[file.available()];
				fileSize += file.available();
				file.read(chunk);
				chunks.add(new Chunk(i, chunk));
				file.close();
			} catch (IOException e) {
				System.out.println("Chunk number " + i + " not found");
			}

		}
		info.close();
	}
	
	/**
	 * 
	 * Gava o ficheiro em pedaços(chunks), e um ficheiro .txt com a informação dos 
	 * pedaços em que o ficheiro foi dividido 
	 * 
	 */
	public void saveChunks() throws IOException {
		FileOutputStream info = new FileOutputStream("Chunks/" + UtilFunc.byteToString(ID) + ".txt");
		info.write((name + '\n').getBytes());
		
		for (int i = 0; i < chunks.size(); ++i) {
			FileOutputStream file = new FileOutputStream("Chunks/"
					+ UtilFunc.byteToString(ID) + "." + i);
			file.write(chunks.get(i).getBytes());

			info.write((UtilFunc.byteToString(ID) + "." + i + "\n").getBytes());
			
			file.close();
		}
		
		info.close();
	}
	
	public void joinChunks() {
		try {
			FileOutputStream file = new FileOutputStream("Share/" + name);
			for (int i = 0; i < chunks.size(); ++i)
				file.write(chunks.get(i).getBytes());
			file.close();
		} catch (IOException e) {
			System.out.println("Couldn't find " + name + "'s chunks");
		}
	}
}
