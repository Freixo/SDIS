package GUI;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ConnectSettings extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private int				  settingsWidth;
	private int				  settingsHeigth;
	private TextFieldPane 	  connectionProperties 	= new TextFieldPane(0,0,"Connection Properties",170,80);
	private	JButton 		  B_CONNECT 			= new JButton("Connect");
	private	JButton 		  cancel 				= new JButton("Cancel");


	public ConnectSettings(int width,int height)
	{
		this.setTitle("Game Settings");

		settingsWidth  = width;
		settingsHeigth = height;

		this.init();
	}

	private void init()
	{
		// JFrame Configuration

		this.setSize(settingsWidth, settingsHeigth);
		this.setLocation((1366-settingsWidth)/2, (768-settingsHeigth)/2);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		//CONNECT
		B_CONNECT.addActionListener(this);
		B_CONNECT.setBounds(20, 130, 90, 20);
		this.add(B_CONNECT);

		//CANCEL
		cancel.addActionListener(this);
		cancel.setBounds(130, 130, 80, 20);
		this.add(cancel);


		//inicializar a tab e os JPanels laterais
		this.connectionPropertiesOptions();
	}
	private void connectionPropertiesOptions()
	{

		connectionProperties.addTextField("Multicast IP",this,100);	
		connectionProperties.addTextField("Port nº",this,50);	
		connectionProperties.addTextField("Backup Folder",this,250);			

		connectionProperties.setEnable(true);
		connectionProperties.setBounds(20, 20, 400, 95);

		this.add(connectionProperties);
	}

	private void saveSettings()
	{
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter("settings.txt"));

			buffer.write("Multicast_IP:" + connectionProperties.getTextFields().get(0).getText() + "\n");
			buffer.write("Port_n:      " + connectionProperties.getTextFields().get(1).getText() + "\n");
			buffer.write("File_Path:   " + connectionProperties.getTextFields().get(2).getText() + "\n");

			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent a) 
	{

		if(a.getActionCommand().equals("Connect"))
		{
			this.saveSettings();
			this.setVisible(false);
		}
		if(a.getActionCommand().equals("Cancel"))
			this.setVisible(false);

	}
}
