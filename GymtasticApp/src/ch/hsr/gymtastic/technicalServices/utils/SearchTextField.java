package ch.hsr.gymtastic.technicalServices.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.hsr.gymtastic.FileFactory;
import ch.hsr.gymtastic.technicalServices.utils.ComponentBorder.Edge;

public class SearchTextField extends JTextField {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public SearchTextField() {
	JLabel searchIconLabel = new JLabel();
	searchIconLabel
		.setIcon(new ImageIcon(FileFactory.class.getResource(FileFactory.icons + FileFactory.searchIcon)));
	ComponentBorder searchCB = new ComponentBorder(searchIconLabel);
	searchCB.setEdge(Edge.LEFT);
	searchCB.install(this);
	setText("Suchen...");
	addFocusListener(new FocusAdapter() {

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
	});

    }
}
