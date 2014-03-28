package FileManage;

import java.io.FileOutputStream;
import java.io.IOException;

public class Chunk {

	private byte[] chunk;
	private int index;

	public Chunk(int i, int byteSize) {
		chunk = new byte[byteSize];
		index = i;
	}

	public Chunk(int i, byte[] chunk2) {
		chunk = chunk2;
		index = i;
	}

	public byte[] getBytes() {
		return chunk;
	}

	public int getIndex() {
		return index;
	}
	public void saveChunk(String fileName) throws IOException {
		
		FileOutputStream file = new FileOutputStream("Chunks/" + fileName + "." + index);
		file.write(chunk);
			
		file.close();
	}
}
