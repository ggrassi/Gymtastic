package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import network.ClientInformation;
import network.RMIServer;

public class DeviceTypeTableModel extends AbstractTableModel implements Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "IP-Adresse", "Gewünschtes Gerät" };
	private final RMIServer rmiServer;

	public DeviceTypeTableModel(RMIServer rmiServer) {
		this.rmiServer = rmiServer;
	this.rmiServer.addObserver(this);
    }

    @Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return rmiServer.getClientsWaitingForAllocation().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ClientInformation client = rmiServer.getClientsWaitingForAllocation()
				.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return client.getHost();
		case 1:
			return client.getDeviceType();
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableChanged(new TableModelEvent(this,TableModelEvent.INSERT));
	}

}

