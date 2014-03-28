import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import GUI.MenuBarSector;

public class PeerGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static JButton B_BACKUP = new JButton("Backup");
	public static JScrollPane SP_OUTPUT = new JScrollPane();
	public static JScrollPane SP_INPUT = new JScrollPane();
	public static JTextArea TA_OUTPUT = new JTextArea();
	public static JTextArea TA_INPUT = new JTextArea();
	private static JMenuBar menuBar = new JMenuBar();


	public PeerGUI(ActionListener listener)throws IOException{
		super("Server");
		BuildGui(listener);
	}
	public void run(){

		/*try {
			TA_OUTPUT.append("My address: " + InetAddress.getLocalHost() + "\n\n");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}*/
	}
	public void BuildGui(ActionListener listener){

		this.setSize(800,425);
		this.setLocation(200,200);
		this.setResizable(false);
		this.setBackground(new java.awt.Color(0,0,255));
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(0x3);

		// JMenuBar configuration
		MenuBarSector file = new MenuBarSector("File");
		String[] fileItems = {"Connect","Disconnect","Open File", "Settings","Exit"};

		for(int i = 0; i < fileItems.length; i++)
			file.addMenuItem(fileItems[i],listener);

		menuBar.add(file);
		menuBar.setBounds(0,0,800,25);

		this.add(menuBar);

		B_BACKUP.setBackground(new java.awt.Color(0,255,0));
		B_BACKUP.setForeground(new java.awt.Color(255,255,255));

		B_BACKUP.setBounds(700, 330, 80, 60);

		B_BACKUP.addActionListener(listener);
		
		this.getContentPane().add(B_BACKUP);

		TA_OUTPUT.setLineWrap(true);
		TA_INPUT.setLineWrap(true);
		SP_OUTPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_OUTPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_OUTPUT.setViewportView(TA_OUTPUT);

		SP_INPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_INPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_INPUT.setViewportView(TA_INPUT);

		this.getContentPane().add(SP_OUTPUT);
		SP_OUTPUT.setBounds(10, 45, 770, 280);

		this.getContentPane().add(SP_INPUT);
		SP_INPUT.setBounds(10, 330, 680, 60);

		this.setVisible(true);
	}
	
	public void setSelectedFile(String selectedFile){
		TA_INPUT.append("\n" + selectedFile);
	}

}

