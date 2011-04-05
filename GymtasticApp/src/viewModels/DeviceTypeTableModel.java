package viewModels;


import java.util.Set;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import domain.DeviceType;

import network.ClientInformation;
import network.RMIServer;

public class DeviceTypeTableModel extends AbstractTableModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] columns = { "IP-Adresse", "Gewünschtes Gerät" };
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
	if (rmiServer != null) {
//	    return rmiServer.getClientsWaitingForAllocation().size();
	    return 0;
	} else {
	    return 0;
	}

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

//	Set<Entry<DeviceType, ClientInformation>> clientSet = rmiServer.getClientsWaitingForAllocation().entrySet();

	
//	Set<Entry<DeviceType, ClientInformation>> clientSet = rmiServer.getClientsWaitingForAllocation().entrySet();
	
	switch (columnIndex) {
	case 0:
	    return "hallo";
	case 1:


	    return "tschau";
	


	}
	return null;
    }

}
