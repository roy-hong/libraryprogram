package LibraryInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoKeywordPopUp extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public NoKeywordPopUp() { //it pops out when the program couldn't find the keyword in the list
	
	setSize(200, 100);
	JPanel PopUpPanel = new JPanel();
	JLabel  PopUpLabel = new JLabel(" No Result for the Keyword ");
	PopUpPanel.add(PopUpLabel);
	add(PopUpPanel);
	
	setVisible(true);
	
	}
}