package ch.hsr.gymtastic.server.presentation.models;

import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import ch.hsr.gymtastic.domain.Squad;

/**
 * The Class SquadComboBoxModel defines the behavior of the ComboBox
 */
public class SquadComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 1L;
	private Map<Integer, Squad> squads;

	/**
	 * Instantiates a new squad combo box model.
	 * 
	 * @param squads
	 *            the squads
	 */
	public SquadComboBoxModel(Map<Integer, Squad> squads) {
		this.squads = squads;
		addElements();
	}

	/**
	 * Instantiates a new squad combo box model.
	 */
	public SquadComboBoxModel() {
	}

	/**
	 * Adds the elements.
	 */
	private void addElements() {
		for (int i : squads.keySet()) {
			addElement(i);
		}
	}
}
