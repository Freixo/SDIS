import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

import Channel.MulticastControlChannel;
import Channel.MulticastDataBackup;
import FileManage.SmashedFile;

public class Peer implements ActionListener{

	private PeerGUI        peerGUI        = null;
	private MulticastControlChannel controlChannel = null;
	private MulticastDataBackup     dataBackupChannel = null;

	
	public static void main(String[] args)throws IOException{
		Peer PEER = new Peer();
		PEER.start();
	}
	
	
	public Peer()throws IOException{
		peerGUI        = new PeerGUI(this);
		controlChannel = new MulticastControlChannel();
		dataBackupChannel = new MulticastDataBackup();
	}
	public void start(){
		controlChannel.start();
		dataBackupChannel.start();
	}
	
	

	private void openFile() throws NoSuchAlgorithmException, IOException{		
		JFileChooser openFrame = new JFileChooser("./");

		int returnValue = openFrame.showOpenDialog(peerGUI);

		if(returnValue == JFileChooser.APPROVE_OPTION){
			dataBackupChannel.setCurrentFile( new SmashedFile(openFrame.getSelectedFile().getName()));
			dataBackupChannel.getCurrentFile().loadFile();

			peerGUI.setSelectedFile(dataBackupChannel.getCurrentFile().getName());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {	
		if(a.getActionCommand().equals("Connect")){
			controlChannel.CONNECT();
			dataBackupChannel.CONNECT();
		}
		if(a.getActionCommand().equals("Disconnect")){
			controlChannel.DISCONNECT();
			dataBackupChannel.DISCONNECT();
		}		
		if(a.getActionCommand().equals("Open File")){
			try {
				try {
					openFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		if(a.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		if(a.getActionCommand().equals("Settings")){
			try {
				SmashedFile file = new SmashedFile("Assassins-Creed-3-Assassin-Game.jpeg");
				file.loadChunks();
				file.joinChunks();
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(a.getActionCommand().equals("Backup")){
			dataBackupChannel.backup();
		}
	}
}

