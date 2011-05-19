package ch.hsr.gymtastic.server.presentation.models;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ch.hsr.gymtastic.domain.Competition;

public class CompetitionComboBoxModel extends DefaultComboBoxModel {
	private static final long serialVersionUID = 1L;
	private List<Competition> competitions;

	public CompetitionComboBoxModel(List<Competition> list) {
		this.competitions = list;
		addElements();
	}

	public CompetitionComboBoxModel() {
	}

	private void addElements() {
		for (Competition c : competitions) {
			addElement(c);
		}
		addElement("Alle");
	}

}
