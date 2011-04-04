package viewModels;

import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import domain.ClientAllocation;
import domain.DeviceType;

import network.ClientInformation;
import network.RMIServer;

public class DeviceTypeTableModel extends AbstractTableModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] columns = {"IP-Adresse","Gewünschtes Gerät"};
    private final RMIServer rmiServer;

    public DeviceTypeTableModel(RMIServer rmiServer) {
	this.rmiServer = rmiServer;
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
	
	Set<Entry<DeviceType, ClientInformation>> clientSet = rmiServer.getClientsWaitingForAllocation().entrySet();
	
	
	switch (columnIndex) {
	case 0:
	case 1:
	

	}
	return null;
    }

}
