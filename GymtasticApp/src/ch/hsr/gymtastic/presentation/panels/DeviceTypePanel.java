package ch.hsr.gymtastic.presentation.panels;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import ch.hsr.gymtastic.application.controller.ClientAllocation;
import ch.hsr.gymtastic.application.editor.DeviceTypeEditor;
import ch.hsr.gymtastic.application.models.DeviceTypeTableModel;
import ch.hsr.gymtastic.presentation.Server;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;

public class DeviceTypePanel extends JPanel {

	private RMIServer rmiServer;
	private JTable tableDevices;
	private TableModel deviceTypeTableModel;
	private JComboBox cmbDeviceType = new JComboBox();
	private JScrollPane scrollPaneTableDevices;

	public DeviceTypePanel(DeviceTypeTableModel deviceTypeTableModel, RMIServer rmiServer2) {
		super();
		this.deviceTypeTableModel = deviceTypeTableModel;
		this.rmiServer = rmiServer2;
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControl = new JPanel();
		this.add(panelControl, BorderLayout.SOUTH);
		panelControl.setLayout(new BorderLayout(0, 0));
		JButton btnAllocateAllDevices = new JButton("Alle Zuweisen");
		btnAllocateAllDevices.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Server.clientAllocation = new ClientAllocation();
				Server.clientAllocation.addAll(rmiServer
						.getClientsWaitingForAllocation());
			}
		});
		panelControl.add(btnAllocateAllDevices, BorderLayout.EAST);

		tableDevices = new JTable();
		tableDevices.setModel(deviceTypeTableModel);
		tableDevices.getColumnModel().getColumn(1).setCellEditor(
				new DeviceTypeEditor(cmbDeviceType));

		scrollPaneTableDevices = new JScrollPane();
		this.add(scrollPaneTableDevices, BorderLayout.CENTER);
		scrollPaneTableDevices.setViewportView(tableDevices);
		
	}

}
