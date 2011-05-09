package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.server.application.controller.GymCupController;

public class AthleteDataTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname", "Jahrgang",
			"Leistungsklasse"};
	private final GymCupController gymCupController;
	

	public AthleteDataTableModel(GymCupController gymCupController) {
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
			return gymCupController.getGymCup().getAllAthletes().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Athlete athlete = gymCupController.getGymCup().getAllAthletes().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return athlete.getFirstName();
		case 1:
			return athlete.getLastName();
		case 2:
			return athlete.getYearOfBirth();
		case 3:
			return athlete.getPrgClass();
		
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}

