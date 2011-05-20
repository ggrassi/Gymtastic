package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.server.application.controller.GymCupController;

public class AthleteDetailTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private String[] columns = { "Gerät", "D-Note", "E-Note 1", "E-Note 2", "E-Note 3", "Bonus", "Penalty",
	    "Gesamtnote" };
    private Athlete athlete;
    private GymCupController gymCupController;

    public AthleteDetailTableModel(Athlete athlete, GymCupController gymCupController) {
	this.athlete = athlete;
	this.gymCupController = gymCupController;
	this.gymCupController.getCompetitionController().addObserver(this);

    }

    @SuppressWarnings("rawtypes")
    Class[] columnTypes = new Class[] { DeviceType.class, Double.class, Double.class, Double.class, Double.class,
	    Double.class, Double.class, Double.class };

    @SuppressWarnings( { "unchecked", "rawtypes" })
    public Class getColumnClass(int columnIndex) {
	return columnTypes[columnIndex];
    }

    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public Class getColumnClass(int columnIndex) {
    // return (Class) getValueAt(0,columnIndex);
    // }

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
	if (athlete != null) {
	    return DeviceType.values().length;
	} else
	    return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	if (athlete != null) {
	    DeviceType deviceType = DeviceType.values()[rowIndex];
	    if (deviceType != null) {
		Mark mark = athlete.getMark(deviceType);
		switch (columnIndex) {
		case 0:
		    return deviceType;
		case 1:
		    return mark.getdMark();
		case 2:
		    return mark.geteMarkOne();
		case 3:
		    return mark.getEmarkTwo();
		case 4:
		    return mark.geteMarkThree();
		case 5:
		    return mark.getBonus();
		case 6:
		    return mark.getPenalty();
		case 7:
		    return mark.getFinalMark();
		}
	    }
	}
	return "";
    }

    public boolean isCellEditable(int row, int col) {
	if (getColumnClass(col).equals(DeviceType.class) || col == (getColumnCount() - 1)) {
	    return false;
	} else {
	    return true;
	}
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
	if (athlete != null) {
	    DeviceType deviceType = DeviceType.values()[rowIndex];
	    if (deviceType != null) {
		Mark mark = athlete.getMark(deviceType);
		switch (columnIndex) {
		case 1:
		    mark.setdMark((Double) value);
		    return;
		case 2:
		    mark.seteMarkOne((Double) value);
		    return;
		case 3:
		    mark.setEmarkTwo((Double) value);
		    return;
		case 4:
		    mark.seteMarkThree((Double) value);
		    return;
		case 5:
		    mark.setBonus((Double) value);
		    return;
		case 6:
		    mark.setPenalty((Double) value);
		    return;
		}
	    }
	}

	fireTableCellUpdated(rowIndex, columnIndex);
	// fireTableDataChanged();
    }

    public void setAthlete(Athlete athlete) {
	this.athlete = athlete;
    }

    public void update(Observable arg0, Object arg1) {
	fireTableDataChanged();
    }

}
