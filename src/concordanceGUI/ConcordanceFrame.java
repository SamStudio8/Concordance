package concordanceGUI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class ConcordanceFrame extends JFrame {
	private String frameTitle;
	
	ConcordanceFrame(String title, int xSize, int ySize, int xPos, int yPos){
		this.frameTitle = title;
		setTitle(this.frameTitle);
		setSize(xSize, ySize);
		setLocation(xPos, yPos);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon("icon.jpg").getImage());
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void displayFrame(){
		setVisible(true);
	}
	
	public void setMenu(JMenuBar menu){
		setJMenuBar(menu);
	}
}
