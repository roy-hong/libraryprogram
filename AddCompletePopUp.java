package LibraryInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddCompletePopUp extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddCompletePopUp() { //it pops out when the adding has finished
		setSize(100, 100);
		JPanel PopUpPanel = new JPanel();
		JLabel  PopUpLabel = new JLabel(" added! ");
		PopUpPanel.add(PopUpLabel);
		add(PopUpPanel);
	
		setVisible(true);
	
	}
}
