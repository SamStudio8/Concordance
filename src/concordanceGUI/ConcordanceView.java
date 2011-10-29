package concordanceGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import concordanceGUI.Panels.ConcordanceLineNumberListPanel;
import concordanceGUI.Panels.ConcordanceOptionsPanel;
import concordanceGUI.Panels.ConcordanceContextDisplayPanel;
import concordanceGUI.Panels.ConcordanceIndexListPanel;

/**
 * Initializes and adds the various panels to the main frame of the application.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceView extends JFrame {
	
	private ConcordanceMenu menu = new ConcordanceMenu();
	private ConcordanceOptionsPanel optionsPanel = new ConcordanceOptionsPanel();
	private ConcordanceIndexListPanel indexListPanel = new ConcordanceIndexListPanel();
	private ConcordanceContextDisplayPanel contextDisplayPanel = new ConcordanceContextDisplayPanel();
	private ConcordanceLineNumberListPanel lineNumberListPanel = new ConcordanceLineNumberListPanel();
	
	/**
	 * Construct the view of the Concordance application.
	 * Sets various options such as the window title, size and location.
	 * Specifies the user cannot resize the window.
	 * 
	 * The menu bar is also added along with the various panels that make up the UI.
	 */
	public ConcordanceView(){
		setTitle("Concordarizer");
		setSize(800, 500);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon("icon.jpg").getImage());
		setResizable(false);
		setLocationRelativeTo(null);
		
		setJMenuBar(menu);
		add(optionsPanel, BorderLayout.NORTH);
		
		add(indexListPanel, BorderLayout.WEST);
		indexListPanel.setPreferredSize(new Dimension(200, 150));
		
		add(contextDisplayPanel, BorderLayout.SOUTH);
		contextDisplayPanel.setPreferredSize(new Dimension(600, 150));
		
		add(lineNumberListPanel, BorderLayout.EAST);
		lineNumberListPanel.setPreferredSize(new Dimension(600, 150));
	}
	
	/**
	 * Set the visibility of the main application frame to true.
	 */
	public void displayView(){
		setVisible(true);
	}
	
	/**
	 * Provides an interface to access the ConcordanceOptionsPanel
	 * @return	The ConcordanceOptionsPanel
	 */
	public ConcordanceOptionsPanel getOptionsPanel(){
		return this.optionsPanel;
	}
	
	/**
	 * Provides an interface to access the ConcordanceIndexListPanel
	 * @return The ConcordanceIndexListPanel
	 */
	public ConcordanceIndexListPanel getIndexListPanel(){
		return this.indexListPanel;
	}
	
	/**
	 * Provides an interface to access the ConcordanceContextDisplayPanel
	 * @return The ConcordanceContextDisplayPanel
	 */
	public ConcordanceContextDisplayPanel getContextDisplayPanel(){
		return this.contextDisplayPanel;
	}
	
	/**
	 * Provides an interface to access the ConcordanceLineNumberListPanel
	 * @return The ConcordanceLineNumberListPanel
	 */
	public ConcordanceLineNumberListPanel getLineNumberListPanel(){
		return this.lineNumberListPanel;
	}
}
