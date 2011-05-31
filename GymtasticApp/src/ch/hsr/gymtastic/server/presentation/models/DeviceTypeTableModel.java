package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.NetworkServerController;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

/**
 * The Class DeviceTypeTableModel defines the behavior of the table.
 */
public class DeviceTypeTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "IP-Adresse", "Gew\u00fcnschtes Ger\u00e4t" };
	private final NetworkServerController networkController;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	/**
	 * Instantiates a new device type table model.
	 * 
	 * @param networkController
	 *            the network controller
	 */
	public DeviceTypeTableModel(NetworkServerController networkController) {
		this.networkController = networkController;
		this.networkController.addObserver(this);
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
		if (networkController != null) {
			return networkController.getClientsWaitingForAllocation().size();
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
		ClientInformation client = getClient(rowIndex);
		switch (columnIndex) {
		case 0:
			return client.getHost();
		case 1:
			return client.getDeviceType();
		}
		return "";
	}

	/**
	 * Gets the clientInformation in combination with its index in the table
	 * 
	 * @param rowIndex
	 *            the row index
	 * @return the client
	 */
	private ClientInformation getClient(int rowIndex) {
		ClientInformation client = networkController
				.getClientsWaitingForAllocation().get(rowIndex);
		return client;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	@Override
	public void setValueAt(Object deviceType, int rowIndex, int columnIndex) {
		getClient(rowIndex).setDeviceType((DeviceType) deviceType);
	}

}
