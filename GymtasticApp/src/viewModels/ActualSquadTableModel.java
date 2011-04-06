package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import network.RMIClient;
import domain.Athlet;

public class ActualSquadTableModel extends AbstractTableModel implements
		Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Athlet ID", "Vorname", "Nachname",
			"Jahrgang", "Leistungsklasse" };
	private final RMIClient rmiClient;

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
		if (rmiClient.getSquad() != null) {
			return rmiClient.getSquad().getAthlets().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Athlet athlet = rmiClient.getSquad().getAthlete(rowIndex);
		// Athlet athlet = new Athlet("Mathias","Fasser","Gutacker");

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
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}
