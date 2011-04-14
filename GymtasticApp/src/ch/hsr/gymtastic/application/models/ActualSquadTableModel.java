package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class ActualSquadTableModel extends AbstractTableModel implements
		Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Athlet ID", "Vorname", "Nachname",
			"Jahrgang", "Leistungsklasse" };
	private Squad squad = null;
	private final NetworkClientController networkController;

	public ActualSquadTableModel(NetworkClientController networkController) {
		this.networkController = networkController;
		this.networkController.addObserver(this);
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (squad != null) {
			return squad.getAthlets().size();
		} else {
			return 0;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		if (columnIndex == 1) {
			networkController.getSquad().getAthlete(rowIndex).setFirstName(
					(String) aValue);
		}

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (squad != null) {

			Athlete athlet = squad.getAthlete(rowIndex);

			switch (columnIndex) {
			case 0:
				return athlet.getAthleteId();
			case 1:
				return athlet.getFirstName();
			case 2:
				return athlet.getLastName();
			case 3:
				return athlet.getYearOfBirth();
			case 4:
				return athlet.getPrgClass();

			}
		}
		return "";

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		squad = networkController.getSquad();
		fireTableDataChanged();
	}

}
