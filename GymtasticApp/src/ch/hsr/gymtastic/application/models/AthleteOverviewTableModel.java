package ch.hsr.gymtastic.application.models;

import java.util.Observable;

import javax.swing.table.AbstractTableModel;
import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;


public class AthleteOverviewTableModel extends AbstractTableModel {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname","Jahrgang","Leistungsklasse","Endnote" };
	private final NetworkClientController networkController;

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public AthleteOverviewTableModel(NetworkClientController networkController) {
		this.networkController = networkController;
//		this.networkController.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (networkController != null) {
			return networkController.getSquad().getAthlets().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Athlete athlete = networkController.getSquad().getAthlete(rowIndex);
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
			return athlete.getMarks().get(DeviceType.FLOOR_EXCERCISE).getFinalMark();
		}
		return "";
	}
	
	

	
}
