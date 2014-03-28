package GUI;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuBarSector extends JMenu{

	private static final long serialVersionUID = 1L;

	public MenuBarSector(String name)
	{
		super(name);
	}
	
	public boolean addMenuItem(String itemName,ActionListener a)
	{
		JMenuItem newItem = new JMenuItem(itemName);
		
		for(int i = 0; i < this.getItemCount(); i++)
			if(this.getItem(i).getActionCommand() == itemName)
				return false;
		
		newItem.addActionListener(a);
		this.add(newItem);
		
		return true;
	}
	
	public boolean addSeparator(int position)
	{
		if(this.getItemCount() < position && position > 0)
			return false;
		
		this.insertSeparator(position);
		
		return true;
	}
}