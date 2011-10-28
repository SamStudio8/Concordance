package concordanceGUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ConcordanceMenu extends JMenuBar{

	private JMenu fileMenu = new JMenu("File");
	private JMenuItem fileMenu_Quit = new JMenuItem("Quit");
	private ConcordanceMenuListener menuListener;
	
	ConcordanceMenu(){
		menuListener = new ConcordanceMenuListener();
		
		add(fileMenu);
		fileMenu.add(fileMenu_Quit).addActionListener(menuListener);
	}
}
