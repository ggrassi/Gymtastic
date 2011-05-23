package ch.hsr.gymtastic.server.presentation.models;

import java.util.Set;
import javax.swing.DefaultComboBoxModel;

/**
 * The Class ProgramClassAthleteComboBoxModel defines the behavior of the
 * ComboBox
 */
public class ProgramClassAthleteComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 1L;
	private Set<String> programClasses;

	/**
	 * Instantiates a new program class athlete combo box model.
	 * 
	 * @param set
	 *            the set
	 */
	public ProgramClassAthleteComboBoxModel(Set<String> set) {
		this.programClasses = set;
		addElements();
	}

	/**
	 * Instantiates a new program class athlete combo box model.
	 */
	public ProgramClassAthleteComboBoxModel() {
	}

	/**
	 * Adds the elements.
	 */
	private void addElements() {
		for (String s : programClasses) {
			addElement(s);
		}
	}
}
