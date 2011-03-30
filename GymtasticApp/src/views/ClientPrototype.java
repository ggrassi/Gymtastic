package views;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import network.RMIClient;
import domain.Dummy;

public class ClientPrototype implements Observer {

	private JFrame frmGymtasticClient;
	private JTextField txtServerIP;
	private JTextField textField;
	private final RMIClient client;
	private Dummy dummy;
	final JButton btnDisconnect = new JButton("Disconnect");
	final JButton btnConnect = new JButton("Connect");

	/**
	 * Launch the application.
	 */
	public static void newClientPrototypeFrame(final RMIClient client) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientPrototype window = new ClientPrototype(client);
					window.frmGymtasticClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public ClientPrototype(RMIClient client) {
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGymtasticClient = new JFrame();
		frmGymtasticClient.setResizable(false);
		frmGymtasticClient.setTitle("Gymtastic - Client Prototype");
		frmGymtasticClient.setBounds(100, 100, 306, 281);
		frmGymtasticClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frmGymtasticClient.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Connection",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frmGymtasticClient.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblServerIP = new JLabel("Server-IP:");
		GridBagConstraints gbc_lblServerIP = new GridBagConstraints();
		gbc_lblServerIP.insets = new Insets(0, 0, 0, 5);
		gbc_lblServerIP.anchor = GridBagConstraints.EAST;
		gbc_lblServerIP.gridx = 0;
		gbc_lblServerIP.gridy = 0;
		panel_2.add(lblServerIP, gbc_lblServerIP);

		txtServerIP = new JTextField(client.getServerIP());
		txtServerIP.setToolTipText("example: 192.168.1.100");
		GridBagConstraints gbc_txtServerIP = new GridBagConstraints();
		gbc_txtServerIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServerIP.gridx = 1;
		gbc_txtServerIP.gridy = 0;
		panel_2.add(txtServerIP, gbc_txtServerIP);
		txtServerIP.setColumns(10);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.EAST;
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enableConnectionPanel();
				try {
					client.disconnect();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		btnDisconnect.setEnabled(false);
		panel_3.add(btnDisconnect);

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					disableConnectionPanel();
					client.connect();

				} catch (Exception e) {

				}
			}

		});
		panel_3.add(btnConnect);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frmGymtasticClient.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_1.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panel_5.add(lblName, gbc_lblName);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_5.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblEnote = new JLabel("E-Note:");
		GridBagConstraints gbc_lblEnote = new GridBagConstraints();
		gbc_lblEnote.anchor = GridBagConstraints.EAST;
		gbc_lblEnote.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnote.gridx = 0;
		gbc_lblEnote.gridy = 1;
		panel_5.add(lblEnote, gbc_lblEnote);

		JComboBox cBeNote = new JComboBox();
		DefaultComboBoxModel cBModel = new DefaultComboBoxModel(new String[] { "0", "1", "2",
				"3", "4", "5", "6", "7", "8", "9" });
		cBeNote.setModel(cBModel);
		GridBagConstraints gbc_cBeNote = new GridBagConstraints();
		gbc_cBeNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBeNote.insets = new Insets(0, 0, 5, 0);
		gbc_cBeNote.gridx = 1;
		gbc_cBeNote.gridy = 1;
		panel_5.add(cBeNote, gbc_cBeNote);

		JLabel lblDnote = new JLabel("D-Note:");
		GridBagConstraints gbc_lblDnote = new GridBagConstraints();
		gbc_lblDnote.anchor = GridBagConstraints.EAST;
		gbc_lblDnote.insets = new Insets(0, 0, 0, 5);
		gbc_lblDnote.gridx = 0;
		gbc_lblDnote.gridy = 2;
		panel_5.add(lblDnote, gbc_lblDnote);

		JComboBox cBdNote = new JComboBox();
		cBdNote.setModel(cBModel);
		GridBagConstraints gbc_cBdNote = new GridBagConstraints();
		gbc_cBdNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBdNote.gridx = 1;
		gbc_cBdNote.gridy = 2;
		panel_5.add(cBdNote, gbc_cBdNote);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.EAST;
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_1.add(panel_4, gbc_panel_4);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setToolTipText("Transmitts Updates to Server");
		panel_4.add(btnUpdate);
	}

	private void disableConnectionPanel() {
		btnConnect.setEnabled(false);
		btnDisconnect.setEnabled(true);
		txtServerIP.setEnabled(false);
	}
	private void enableConnectionPanel() {
		btnConnect.setEnabled(true);
		btnDisconnect.setEnabled(false);
		txtServerIP.setEnabled(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

}
