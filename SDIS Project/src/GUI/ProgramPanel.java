package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class ProgramPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static JButton B_CONNECT = new JButton("Connect");
	public static JButton B_DISCONNECT = new JButton("Disconnect");
	public static JButton B_SEND = new JButton("Send");
	public static JScrollPane SP_OUTPUT = new JScrollPane();
	public static JScrollPane SP_INPUT = new JScrollPane();
	public static JTextArea TA_OUTPUT = new JTextArea();
	public static JTextArea TA_INPUT = new JTextArea();	
	
	private int				  panelWidth;
	private int				  panelHeight;
	private Image			  background;
	private Graphics		  graphics;
	
	public ProgramPanel(int width,int height)
	{
		this.panelWidth   = width;
		this.panelHeight  = height;
		
		//this.setFocusable(true);
		//this.requestFocusInWindow();
		
		//this.init();
	}
	private void init(){
		//this.setBackground(new java.awt.Color(0,0,255));
		
		B_CONNECT.setBackground(new java.awt.Color(0,0,255));
		B_CONNECT.setForeground(new java.awt.Color(255,255,255));
		B_DISCONNECT.setBackground(new java.awt.Color(255,0,0));
		B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
		B_SEND.setBackground(new java.awt.Color(0,255,0));
		B_SEND.setForeground(new java.awt.Color(255,255,255));

		this.add(B_CONNECT);
		this.add(B_DISCONNECT);
		this.add(B_SEND);
		B_CONNECT.setBounds(130, 15, 110, 25);
		B_DISCONNECT.setBounds(250, 15, 110, 25);
		B_SEND.setBounds(400, 330, 70, 60);

		TA_OUTPUT.setLineWrap(true);
		TA_INPUT.setLineWrap(true);
		SP_OUTPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_OUTPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_OUTPUT.setViewportView(TA_OUTPUT);

		SP_INPUT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_INPUT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_INPUT.setViewportView(TA_INPUT);

		this.add(SP_OUTPUT);
		SP_OUTPUT.setBounds(10, 45, 460, 280);

		this.add(SP_INPUT);
		SP_INPUT.setBounds(10, 330, 380, 60);

		this.setVisible(true);
	}
	

}
