package ch.hsr.gymtastic.client.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

/**
 * The Class AthleteOverviewTableModel
 */
public class AthleteOverviewTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private String[] columns = { "Vorname", "Nachname", "Jahrgang", "Leistungsklasse", "Endnote" };
    private Squad squad;
    private DeviceType deviceType;

    /**
     * Instantiates a new athlete overview table model.
     * 
     * @param actualSquad
     *            the actual squad
     * @param deviceType
     *            the device type
     */
    public AthleteOverviewTableModel(Squad actualSquad, DeviceType deviceType) {
	this.squad = actualSquad;
	this.deviceType = deviceType;
	this.squad.addObserver(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int columnIndex) {
	return columns[columnIndex];
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
	return columns.length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
	if (squad != null) {
	    return squad.getAthlets().size();
	} else {
	    return 0;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
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
	    if (athlete.getMarks().get(deviceType) == null) {
		return "0";
	    } else {
		return athlete.getMarks().get(deviceType).getFinalMark();
	    }
	}
	return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable arg0, Object arg1) {
	fireTableDataChanged();
    }

}
