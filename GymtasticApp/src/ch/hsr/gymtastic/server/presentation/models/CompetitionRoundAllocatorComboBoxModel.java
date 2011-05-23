package ch.hsr.gymtastic.server.presentation.models;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ch.hsr.gymtastic.domain.Competition;

/**
 * The Class CompetitionRoundAllocatorComboBoxModel defines the behavior for a
 * comboBox
 */
public class CompetitionRoundAllocatorComboBoxModel extends
		DefaultComboBoxModel {

	private static final long serialVersionUID = 1L;
	private List<Competition> competitions;

	/**
	 * Instantiates a new competition round allocator combo box model.
	 * 
	 * @param list
	 *            the list
	 */
	public CompetitionRoundAllocatorComboBoxModel(List<Competition> list) {
		this.competitions = list;
		addElements();
	}

	/**
	 * Instantiates a new competition round allocator combo box model.
	 */
	public CompetitionRoundAllocatorComboBoxModel() {
	}

	/**
	 * Adds the elements.
	 */
	private void addElements() {
		for (Competition c : competitions) {
			addElement(c);
		}
	}

}
