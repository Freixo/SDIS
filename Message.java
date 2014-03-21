package MessageManage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import FileManage.Chunk;
import FileManage.File;
import Misc.UtilFunc;

public class Message {
	private Vector<byte[]> messages = new Vector<byte[]>();
	static private String CRLF = "0xDA";
	private Scanner scanner = new Scanner(System.in);

	public void createSendMessage(int cmd, File myFile) {
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

	private void createREMOVED(File myFile) {
		String header;
		header = "REMOVED " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunk();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}
	}

	private void createDELETE(File myFile) {
		String message;
		message = "DELETE " + UtilFunc.byteToString(myFile.getID()) + " "
				+ CRLF + " " + CRLF + " ";

		messages.add(message.getBytes());
	}

	private void createCHUNK(File myFile) {
		String header;
		header = "CHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunk();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " " + fileChunks.get(i).getChunk() + " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}

	}

	private void createGETCHUNK(File myFile) {
		String header;
		header = "GETCHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunk();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}

	}

	private void createSTORED(File myFile) {
		String header;
		header = "STORED " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunk();

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + CRLF + " " + CRLF
					+ " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}
	}

	private void createPUTCHUNK(File myFile) {
		String header;
		header = "PUTCHUNK " + myFile.getVersion() + " "
				+ UtilFunc.byteToString(myFile.getID()) + " ";
		Vector<Chunk> fileChunks = myFile.getChunk();

		int ReplicationDeg;
		while (true)
			try {
				System.out.println("Quantos Backups do ficheiro quer fazer?");
				ReplicationDeg = scanner.nextInt();
				break;
			} catch (InputMismatchException ime) {
				System.out.println("Input Inválido");
				scanner.next();
			}

		for (int i = 0; i < fileChunks.size(); ++i) {
			String message = header;
			message += fileChunks.get(i).getIndex() + " " + ReplicationDeg
					+ " " + CRLF + " " + CRLF + " "
					+ fileChunks.get(i).getChunk() + " ";
			messages.add(message.getBytes());
			System.out.println(message);
		}
	}

	public void Menu() throws NoSuchAlgorithmException{
		String Answer;
		System.out.println("Qual o nome do ficheiro que quer utilizar?");
		File myFile;
		while (true)
			try {
				Answer = scanner.next();
				myFile = new File(Answer);
				break;
			} catch (IOException ioe) {
				System.out
						.println("Esse ficheiro não existe. Tente outra vez.");
			}
		int num;
		while (true)
			try {
				System.out.println("O que pretende fazer?");
				System.out.println("1 - Criar um Backup do ficheiro.");
				System.out.println("2 - Fazer Store (Nao é isto mas é para testes).");
				System.out.println("3 - Receber o Ficheiro.(também não faz muito sentido agora).");
				System.out.println("4 - Mandar o Ficheiro para a rede.");
				System.out.println("5 - Remover o ficheiro da rede.");
				System.out.println("6 - Remover o ficheiro do mer computador.");

				num = scanner.nextInt();
				break;
			} catch (InputMismatchException ime) {
				System.out.println("Input Inválido");
				scanner.next();
			}

		createSendMessage(num, myFile);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			IOException {
		Message msg = new Message();
		while (true)
			msg.Menu();
	}
}
