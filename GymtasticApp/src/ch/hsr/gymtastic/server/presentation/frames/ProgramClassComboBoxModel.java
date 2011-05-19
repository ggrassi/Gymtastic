package ch.hsr.gymtastic.server.presentation.frames;

import java.util.Set;

import javax.swing.DefaultComboBoxModel;


public class ProgramClassComboBoxModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
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

	}

}
