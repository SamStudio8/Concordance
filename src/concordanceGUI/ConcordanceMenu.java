package concordanceGUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import concordanceGUI.Listeners.ConcordanceMenuListener;

/**
 * Default menu bar for the Concordance application with a File > Quit option.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceMenu extends JMenuBar{

	private JMenu fileMenu = new JMenu("File");
	private JMenuItem fileMenu_Quit = new JMenuItem("Quit");
	private ConcordanceMenuListener menuListener;
	
	/**
	 * Constructs the ConcordanceMenu with MenuListener and adds the available menu options.
	 */
	ConcordanceMenu(){
		menuListener = new ConcordanceMenuListener();
		add(fileMenu);
		fileMenu.add(fileMenu_Quit).addActionListener(menuListener);
	}
}
