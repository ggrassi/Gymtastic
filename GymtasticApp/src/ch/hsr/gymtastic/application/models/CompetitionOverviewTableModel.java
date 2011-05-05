package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.application.controller.server.GymCupController;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

public class CompetitionOverviewTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Beschreibung", "Datum" };
	private GymCupController gymCupController;

	public CompetitionOverviewTableModel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		this.gymCupController.getGymCup().addObserver(this);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (gymCupController.getGymCup() != null) {
			if (gymCupController.getGymCup().getCompetitions() != null) {
				return gymCupController.getGymCup().getCompetitions().size();
			} else
				return 0;
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Competition competition = gymCupController.getGymCup()
				.getCompetitions().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return competition.getDescription();
		case 1:
			return DateFormatConverter.convertDateToString(competition
					.getDate());
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}
