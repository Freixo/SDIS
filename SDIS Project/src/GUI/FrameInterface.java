package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameInterface extends JFrame implements ActionListener{

	public static void main(String[] args){

		new FrameInterface().setVisible(true);
	}	

	private static final long 	 serialVersionUID = 1L;
	
	//Frame Dimensions
	public  Dimension  dimension 	 = new Dimension(800,600);
	
	//Top Bar Dimensions
	private JMenuBar   menuBar       = new JMenuBar();
	private int		   menuBarHeight = 25;
	
	private ConnectSettings	connectSettings = new ConnectSettings(450,190);
	private ProgramPanel programPanel = new ProgramPanel(20,20);

	public FrameInterface(){
		super("Backup System");
		this.init();
	}

	private void init(){

		// JFrame configuration
		this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
		this.setLocation((1366-(int)dimension.getWidth())/2, (768-(int)dimension.getHeight())/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		programPanel.setBackground(new java.awt.Color(0,255,0));
		this.addPanel(programPanel);
		
		this.initTopBar();		
	}
	private void initTopBar(){
		// JMenuBar configuration
		MenuBarSector 	 file	 = new MenuBarSector("File");
		MenuBarSector 	 view	 = new MenuBarSector("View");

		String[] fileItems = {"Connect","Disconnect","Open File","Exit"};
		String[] viewItems = {"Settings"};

		for(int i = 0; i < fileItems.length; i++)
			file.addMenuItem(fileItems[i],this);

		for(int i = 0; i < viewItems.length; i++)
			view.addMenuItem(viewItems[i],this);

		menuBar.add(file);
		menuBar.add(view);
		menuBar.setBounds(0,0,(int)dimension.getWidth(),menuBarHeight);
		this.add(menuBar);
	}
	private void connectSettingsFrame()
	{
		connectSettings.setVisible(true);
	}
	private void addPanel(JPanel panel)
	{
		panel.setBounds(0,menuBarHeight,(int)dimension.getWidth(),(int)dimension.getHeight()-menuBarHeight);
		this.getContentPane().add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent a) {		


		if(a.getActionCommand().equals("Connect")){
			this.connectSettingsFrame();
		}
		if(a.getActionCommand().equals("Disconnect")){

		}		
		if(a.getActionCommand().equals("Open File")){

		}
		if(a.getActionCommand().equals("Exit")){

		}
		if(a.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		if(a.getActionCommand().equals("Settings")){

		}
	}	

}
