package ch.hsr.gymtastic.server.presentation;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.hsr.gymtastic.server.presentation.ComponentBorder.Edge;

/**
 * The Class SearchTextField is a JTextField optimized for a search field.
 */
public class SearchTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The Class SearchFieldFocusListener deletes/sets  "Suchen..." text, when the focus is gained/lost.
	 */
	private class SearchFieldFocusListener extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent arg0) {
			if (getText().equals("Suchen...")) {
				setText("");
			}

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if (getText().equals("")) {
				setText("Suchen...");
			}
		}
		
	}
	
	/**
	 * Instantiates a new search text field.
	 */
	public SearchTextField() {
		JLabel searchIconLabel = new JLabel();
		searchIconLabel.setIcon(new ImageIcon(FileFactory.class
				.getResource(FileFactory.icons + FileFactory.searchIcon)));
		ComponentBorder searchCB = new ComponentBorder(searchIconLabel);
		searchCB.setEdge(Edge.LEFT);
		searchCB.install(this);
		setText("Suchen...");
		addFocusListener(new SearchFieldFocusListener());

	}
}
