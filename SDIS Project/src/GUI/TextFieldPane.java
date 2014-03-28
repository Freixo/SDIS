package GUI;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TextFieldPane extends JPanel{

	private static final long 				serialVersionUID = 1L;
	//private int				  				panelWidth       = 120;
	private int 							layerSize		 = 110;
	private Vector<JRadioButton>			radioButtons 	 = new Vector<JRadioButton>();
	private Vector<JTextField>				textFields 		 = new Vector<JTextField>();
	
	public TextFieldPane(int x, int y,String title,int panelWidth,int panelHeight)
	{
		//this.panelWidth  = panelWidth;

		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(null);
	}
	public boolean addTextField(String textFieldName,ActionListener a, int width)
	{
		JLabel textFieldLabel = new JLabel(textFieldName);
		textFieldLabel.setBounds(20, (radioButtons.size()+textFields.size()+1)*20, 90, 20);
		this.add(textFieldLabel);

		
		JTextField textField = new JTextField(5);
		textField.setActionCommand(textFieldName+"k");
		textField.addActionListener(a);
		textField.setBounds(layerSize+10, (radioButtons.size()+textFields.size()+1)*21, width, 20);
		this.add(textField);
		textFields.add(textField);
		
		return true;
	}
	
	public boolean addRadioButton(String buttonName,ActionListener a)
	{
		JRadioButton newButton = new JRadioButton(buttonName);
		newButton.setBounds(15, (radioButtons.size()+textFields.size()+1)*20+10, 140, 20);
		newButton.addActionListener(a);
		newButton.setSelected(true);
		this.add(newButton);
		radioButtons.add(newButton);
		
		return true;
	}
	
	public void setEnable(boolean enable)
	{
		for(int i = 0; i < radioButtons.size(); i++)
			radioButtons.get(i).setEnabled(enable);
		
		for(int i = 0; i < textFields.size(); i++)
			textFields.get(i).setEnabled(enable);
	}
	public Vector<JRadioButton> getRadioButtons()
	{
		return this.radioButtons;
	}
	public Vector<JTextField> getTextFields()
	{
		return this.textFields;
	}
}



