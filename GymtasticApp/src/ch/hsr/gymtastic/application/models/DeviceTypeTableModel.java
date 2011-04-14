package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.application.controller.NetworkServerController;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;


public class DeviceTypeTableModel extends AbstractTableModel implements
		Observer {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "IP-Adresse", "Gewünschtes Gerät" };
	private final NetworkServerController networkController;

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public DeviceTypeTableModel(NetworkServerController networkController) {
		this.networkController = networkController;
		this.networkController.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (networkController != null) {
			return networkController.getClientsWaitingForAllocation().size();
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
		ClientInformation client = networkController.getClientsWaitingForAllocation()
				.get(rowIndex);
		System.out.println(client.getHost());
		return client;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
		System.out.println("TABLE UPDATE");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1)
			return true;
		return false;
	}

	@Override
	public void setValueAt(Object deviceType, int rowIndex, int columnIndex) {
		getClient(rowIndex).setDeviceType((DeviceType) deviceType);
	}

}
