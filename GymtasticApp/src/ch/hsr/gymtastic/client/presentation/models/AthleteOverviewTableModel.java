package ch.hsr.gymtastic.client.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

public class AthleteOverviewTableModel extends AbstractTableModel implements
		Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname", "Jahrgang",
			"Leistungsklasse", "Endnote" };
	private Squad squad;
	private DeviceType deviceType;

	public AthleteOverviewTableModel(Squad actualSquad, DeviceType deviceType) {
		this.squad = actualSquad;
		this.deviceType = deviceType;
		this.squad.addObserver(this);
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
			return squad.getAthlets().size();
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
			if(athlete.getMarks().get(deviceType) == null){
				return "0";
			}else{
				return athlete.getMarks().get(deviceType).getFinalMark();
			}
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}
