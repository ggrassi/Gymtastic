package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class AthleteOverviewTableModel extends AbstractTableModel implements
		Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname", "Jahrgang",
			"Leistungsklasse", "Endnote" };
	private final SquadController squadController;
	private Squad squad = null;

	public AthleteOverviewTableModel(SquadController squadController) {
		this.squadController = squadController;
		squadController.addObserver(this);
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
		if (squad != null) {
			return squadController.getSquad().getAthlets().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Athlete athlete = squad.getAthlete(rowIndex);
		switch (columnIndex) {
		case 0:
			return athlete.getFirstName();
		case 1:
			return athlete.getLastName();
		case 2:
			return athlete.getYearOfBirth();
		case 3:
			return athlete.getPrgClass();
		case 4:
//			return athlete.getMarks().get(DeviceType.FLOOR_EXCERCISE)
//					.getFinalMark();
			return "";
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		squad = squadController.getSquad();
		fireTableDataChanged();
	}

}
