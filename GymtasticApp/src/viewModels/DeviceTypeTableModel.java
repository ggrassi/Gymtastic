package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import network.ClientInformation;
import network.RMIServer;
import domain.DeviceType;

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
	if (rmiServer != null) {
	    return rmiServer.getClientsWaitingForAllocation().size();
	} else {
	    return 0;
	}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	ClientInformation client = getClient(rowIndex);
	switch (columnIndex) {
	case 0:
	    return client.getHost();
	case 1:
	    return client.getDeviceType();
	}
	return "";
    }

	private ClientInformation getClient(int rowIndex) {
		ClientInformation client = rmiServer.getClientsWaitingForAllocation().get(rowIndex);
		return client;
	}

    @Override
    public void update(Observable arg0, Object arg1) {
	fireTableChanged(new TableModelEvent(this, TableModelEvent.INSERT));
    }

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 1)
			return true;
		return false;
	}

	@Override
	public void setValueAt(Object deviceType, int rowIndex, int columnIndex) {
		getClient(rowIndex).setDeviceType((DeviceType) deviceType);
	}
    

}
