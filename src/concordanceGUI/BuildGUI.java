package concordanceGUI;

public class BuildGUI {
	
	public BuildGUI(){
		
		ConcordanceModel m = new ConcordanceModel();
		View v = new View();
		ConcordanceController c = new ConcordanceController(v, m);
		v.displayView();
	} 	
}
