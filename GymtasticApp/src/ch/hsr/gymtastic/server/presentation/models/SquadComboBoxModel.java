package ch.hsr.gymtastic.server.presentation.models;

import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import ch.hsr.gymtastic.domain.Squad;

public class SquadComboBoxModel extends DefaultComboBoxModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Map<Integer, Squad> squads;

    public SquadComboBoxModel(Map<Integer, Squad> squads) {
	this.squads = squads;
	addElements();
    }

    public SquadComboBoxModel() {
    }

    private void addElements() {
	for (int i : squads.keySet()) {
	    addElement(i);
	}
    }
}
