package concordanceGUI;

import javax.swing.JOptionPane;

import concordanceGUI.Listeners.*;

/**
 * Reacts to application state changes detected via the various listeners.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceController {
	
	private ConcordanceModel model;
	private ConcordanceView view;

	/**
	 * Construct the ConcordanceController.
	 * The Controller has access to both the application's Views and Model.
	 * The Controller also passes itself to the listeners so they can use its methods.
	 * 
	 * @param v	The ConcordanceView that contains the various display panels.
	 * @param m	The ConcordanceModel that contains the application data.
	 */
	public ConcordanceController(ConcordanceView v, ConcordanceModel m) {
		this.model = m;
		this.view = v;
		
		v.getOptionsPanel().addButtonListener(new ConcordanceButtonListener(this));
		v.getIndexListPanel().addListSelectionListener(new ConcordanceListSelectionListener(this));
	}
	
	/**
	 * Requests the construction of a ConcordanceBuilder.
	 * Called by the push of the Concordanize button, this passes the user 
	 * input to both the index and source text path fields to the model.
	 * 
	 * If the model returns false, construction of a ConcordanceBuilder failed,
	 * caused by one or both of the input files being invalid or missing.
	 * A dialog is shown to the user to notify them of the error.
	 */
	public void startConcordance(){		
		if(model.startConcordanizer(view.getOptionsPanel().getIndexPath(),view.getOptionsPanel().getTextPath())){
			view.getIndexListPanel().buildIndexList(model.getConcordance().getOrderedIndex(), model.getConcordance().getOccurrencesForIndexWords());
		}
		else{
			JOptionPane.showMessageDialog(view, "One or both of your specified input files could not be loaded.\nPlease ensure you have entered the correct paths and try again.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads the Concordance data for the currently selected entry in the JList.
	 * Called by the change of selection in the JList of index words or phrases,
	 * this method updates the contextDisplayPanel and lineNumberDisplayPanel 
	 * with the context and line numbers of the index word or phrase selected by
	 * the user in the JList on the indexListPanel.
	 * 
	 * @param listSelect The index of the currently selected JList item
	 */
	public void loadResult(int listSelect){
		String forWord = model.getConcordance().getOrderedIndex().get(listSelect);
		view.getContextDisplayPanel().displayResult(model.getConcordance().getContext(forWord));
		view.getLineNumberListPanel().displayResult(model.getConcordance().getLineNumbers(forWord));
	}
}
