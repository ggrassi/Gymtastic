package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import network.RMIClient;
import domain.Athlet;
import domain.Squad;

public class ActualSquadTableModel extends AbstractTableModel implements Observer {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] columns = { "Athlet ID", "Vorname", "Nachname", "Jahrgang", "Leistungsklasse" };
    private final RMIClient rmiClient;
    private Squad squad = null;

    public ActualSquadTableModel(RMIClient rmiClient) {
	this.rmiClient = rmiClient;
	this.rmiClient.addObserver(this);
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
	Athlet athlete = rmiClient.getSquad().getAthlete(rowIndex);

	if (columnIndex == 1) {
	    athlete.setFirstName((String) aValue);
	}

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	if (squad != null) {

	    Athlet athlet = squad.getAthlete(rowIndex);

	    switch (columnIndex) {
	    case 0:
		return athlet.getAthletId();
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
	squad = rmiClient.getSquad();
	fireTableDataChanged();
    }

}
