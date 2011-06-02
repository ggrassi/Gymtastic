package ch.hsr.gymtastic.server.presentation.models;

import java.util.Set;

import javax.swing.DefaultComboBoxModel;

/**
 * The Class ProgramClassComboBoxModel defines the default behavior of the
 * ComboBox
 */
public class ProgramClassComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 1L;
	private Set<String> programClasses;

	public ProgramClassComboBoxModel(Set<String> set) {
		this.programClasses = set;
		addElements();
	}

	public ProgramClassComboBoxModel() {
	}

	private void addElements() {
		for (String s : programClasses) {
			addElement(s);
		}
		addElement("Alle");

	}

}
