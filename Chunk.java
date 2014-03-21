package FileManage;

public class Chunk {
	
	private byte[] chunk;
	private int index;
	
	public Chunk(int i, int byteSize)
	{
		chunk = new byte[byteSize];
		index = i;
	}
	
	public byte[] getChunk()
	{
		return chunk;
	}
	
	public int getIndex()
	{
		return index;
	}
}
