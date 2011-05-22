package ch.hsr.gymtastic.server.presentation.models;

import java.util.Set;
import javax.swing.DefaultComboBoxModel;

public class ProgramClassAthleteComboBoxModel extends DefaultComboBoxModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Set<String> programClasses;

    public ProgramClassAthleteComboBoxModel(Set<String> set) {
	this.programClasses = set;
	addElements();
    }

    public ProgramClassAthleteComboBoxModel() {
    }

    private void addElements() {
	for (String s : programClasses) {
	    addElement(s);
	}
    }
}
