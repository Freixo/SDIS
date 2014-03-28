package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionsPanel extends JPanel
{
	private static final long 		serialVersionUID = 1L;
	private int				  		xCoord;
	private int				  		yCoord;
	private Vector<JRadioButton>	radioButtons 	 = new Vector<JRadioButton>();
	
	public OptionsPanel(int x, int y,String title,int panelWidth,int panelHeight)
	{
		this.xCoord    		 = x;
		this.yCoord    		 = y;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setBounds(xCoord, yCoord, panelWidth, panelHeight);
	}
	private void refreshPanel()
	{
		this.setLayout(new GridLayout(radioButtons.size(), 1, 0, 0));
	}
	
	public void addRadioButton(String buttonName,ActionListener a)
	{
		JRadioButton newButton = new JRadioButton(buttonName);
		newButton.addActionListener(a);
		this.add(newButton);
		radioButtons.add(newButton);
		
		this.refreshPanel();
	}
	public Vector<JRadioButton> getRadioButtons()
	{
		return this.radioButtons;
	}
}
