package ch.hsr.gymtastic.server.presentation.models;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ch.hsr.gymtastic.domain.Competition;

/**
 * The Class CompetitionComboBoxModel defines the beavior of the ComboBoxes
 */
public class CompetitionComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 1L;
	private List<Competition> competitions;

	/**
	 * Instantiates a new competition combo box model.
	 * 
	 * @param list
	 *            the list
	 */
	public CompetitionComboBoxModel(List<Competition> list) {
		this.competitions = list;
		addElements();
	}

	/**
	 * Instantiates a new competition combo box model.
	 */
	public CompetitionComboBoxModel() {
	}

	/**
	 * Adds the Competitions to the ComboBox 
	 */
	private void addElements() {
		for (Competition c : competitions) {
			addElement(c);
		}
		addElement("Alle");
	}

}
