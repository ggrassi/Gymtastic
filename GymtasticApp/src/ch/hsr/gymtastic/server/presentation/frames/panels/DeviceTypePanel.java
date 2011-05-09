package ch.hsr.gymtastic.server.presentation.frames.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import ch.hsr.gymtastic.server.application.controller.ClientAllocator;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.application.controller.NetworkServerController;
import ch.hsr.gymtastic.server.application.editor.DeviceTypeEditor;
import ch.hsr.gymtastic.server.presentation.models.DeviceTypeTableModel;

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
	private ClientAllocator clientAllocator;
	private GymCupController gymCupController;

	public DeviceTypePanel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		this.networkController = gymCupController.getNetworkController();
		this.clientAllocator = gymCupController.getClientAllocator();
		initGUI();
		initListeners();
	}

	private void initListeners() {
		btnAllocateAllDevices.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				allocateClients();
				gymCupController.sendGymCupInfosToClients();
				
			}

			private void allocateClients() {
				clientAllocator.addAll(networkController.getClientsWaitingForAllocation());
			}
		});

	}

	private void initGUI() {
		setLayout(new BorderLayout(0, 0));
		panelControl = new JPanel();
		this.add(panelControl, BorderLayout.SOUTH);
		panelControl.setLayout(new BorderLayout(0, 0));
		btnAllocateAllDevices = new JButton("Alle Zuweisen");

		panelControl.add(btnAllocateAllDevices, BorderLayout.EAST);

		tableDevices = new JTable();
		deviceTypeTableModel = new DeviceTypeTableModel(networkController);
		tableDevices.setModel(deviceTypeTableModel);
		tableDevices.getColumnModel().getColumn(1)
				.setCellEditor(new DeviceTypeEditor(cmbDeviceType));

		scrollPaneTableDevices = new JScrollPane();
		this.add(scrollPaneTableDevices, BorderLayout.CENTER);
		scrollPaneTableDevices.setViewportView(tableDevices);

	}

}
