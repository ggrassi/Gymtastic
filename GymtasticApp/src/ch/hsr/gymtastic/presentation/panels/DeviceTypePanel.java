package ch.hsr.gymtastic.presentation.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import ch.hsr.gymtastic.application.controller.ClientAllocation;
import ch.hsr.gymtastic.application.controller.NetworkServerController;
import ch.hsr.gymtastic.application.editor.DeviceTypeEditor;
import ch.hsr.gymtastic.application.models.DeviceTypeTableModel;
import ch.hsr.gymtastic.presentation.Server;

public class DeviceTypePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NetworkServerController networkController;
	private JTable tableDevices;
	private TableModel deviceTypeTableModel;
	private JComboBox cmbDeviceType = new JComboBox();
	private JScrollPane scrollPaneTableDevices;
	private JPanel panelControl;
	private JButton btnAllocateAllDevices;

	public DeviceTypePanel(DeviceTypeTableModel deviceTypeTableModel,
			final NetworkServerController networkController) {

		initGUI();
		initListeners();
		this.deviceTypeTableModel = deviceTypeTableModel;
		this.networkController = networkController;
		this.setLayout(new BorderLayout(0, 0));
	}

	private void initListeners() {
		btnAllocateAllDevices.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Server.clientAllocation = new ClientAllocation();
				Server.clientAllocation.addAll(networkController
						.getClientsWaitingForAllocation());
			}
		});

	}

	private void initGUI() {
		panelControl = new JPanel();
		this.add(panelControl, BorderLayout.SOUTH);
		panelControl.setLayout(new BorderLayout(0, 0));
		btnAllocateAllDevices = new JButton("Alle Zuweisen");
		
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
