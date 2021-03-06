package ch.hsr.gymtastic.client.presentation.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.client.application.controller.NetworkClientController;
import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.domain.DeviceType;

/**
 * The Class ClientConnectionFrame.
 */
public final class ClientConnectionFrame {

	private JFrame frmClientConnection;
	private JLabel lblIpAddress = new JLabel("IP-Adresse:");
	private JLabel lblDeviceType = new JLabel("Ger\u00E4t:");
	private JTextField txtIpAddress;
	private JComboBox cmbDeviceType = new JComboBox();
	private JPanel panelButton = new JPanel();
	private JPanel panelConnection = new JPanel();
	private JButton btnConnect = new JButton("Verbinden");
	private JButton btnAbbrechen = new JButton("Abbrechen");
	private NetworkClientController networkController;
	private final SquadController squadController;

	/**
	 * Launch the client application where you can connect to the servers IP.
	 * 
	 * @param args
	 *            the arguments
	 */

	public static void main(String[] args) {
		setLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientConnectionFrame window = new ClientConnectionFrame();
					window.frmClientConnection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the application and initiates the Client frame.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private ClientConnectionFrame() throws Exception {
		this.squadController = new SquadController();
		this.networkController = new NetworkClientController();
		initGUI();
		initListeners();
	}

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmClientConnection.dispose();
			}
		});
		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					networkController.setServerIP(txtIpAddress.getText());
					networkController.connect((DeviceType) cmbDeviceType
							.getSelectedItem());
					ClientFrame.newClientFrame(squadController,
							networkController);
					frmClientConnection.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmClientConnection,
							"Die Verbindung zum Server ist fehlgeschlagen.",
							"Verbindungsfehler", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initGUI() {
		frmClientConnection = new JFrame();
		frmClientConnection.setTitle("Verbindung");
		frmClientConnection.setResizable(false);
		frmClientConnection.setBounds(100, 100, 345, 153);
		frmClientConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 342, 0 };
		gridBagLayout.rowHeights = new int[] { 91, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		frmClientConnection.getContentPane().setLayout(gridBagLayout);

		panelConnection.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Informationen",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelConnection = new GridBagConstraints();
		gbc_panelConnection.fill = GridBagConstraints.BOTH;
		gbc_panelConnection.insets = new Insets(0, 0, 5, 0);
		gbc_panelConnection.gridx = 0;
		gbc_panelConnection.gridy = 0;
		frmClientConnection.getContentPane().add(panelConnection,
				gbc_panelConnection);
		GridBagLayout gbl_panelConnection = new GridBagLayout();
		gbl_panelConnection.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelConnection.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelConnection.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelConnection.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		panelConnection.setLayout(gbl_panelConnection);

		GridBagConstraints gbc_lblIpAddress = new GridBagConstraints();
		gbc_lblIpAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpAddress.anchor = GridBagConstraints.EAST;
		gbc_lblIpAddress.gridx = 0;
		gbc_lblIpAddress.gridy = 0;
		panelConnection.add(lblIpAddress, gbc_lblIpAddress);

		txtIpAddress = new JTextField();
		lblIpAddress.setLabelFor(txtIpAddress);
		lblIpAddress.setDisplayedMnemonic(KeyEvent.VK_I);
		GridBagConstraints gbc_txtIpAddress = new GridBagConstraints();
		gbc_txtIpAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtIpAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpAddress.gridx = 1;
		gbc_txtIpAddress.gridy = 0;
		panelConnection.add(txtIpAddress, gbc_txtIpAddress);
		txtIpAddress.setColumns(10);

		GridBagConstraints gbc_lblDeviceType = new GridBagConstraints();
		gbc_lblDeviceType.anchor = GridBagConstraints.WEST;
		gbc_lblDeviceType.insets = new Insets(0, 0, 0, 5);
		gbc_lblDeviceType.gridx = 0;
		gbc_lblDeviceType.gridy = 1;
		panelConnection.add(lblDeviceType, gbc_lblDeviceType);

		lblDeviceType.setLabelFor(cmbDeviceType);
		lblDeviceType.setDisplayedMnemonic(KeyEvent.VK_G);
		GridBagConstraints gbc_cmbDeviceType = new GridBagConstraints();
		gbc_cmbDeviceType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDeviceType.gridx = 1;
		gbc_cmbDeviceType.gridy = 1;
		panelConnection.add(cmbDeviceType, gbc_cmbDeviceType);
		FlowLayout fl_panelButton = (FlowLayout) panelButton.getLayout();
		fl_panelButton.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panelButton = new GridBagConstraints();
		gbc_panelButton.anchor = GridBagConstraints.NORTH;
		gbc_panelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelButton.gridx = 0;
		gbc_panelButton.gridy = 1;
		frmClientConnection.getContentPane().add(panelButton, gbc_panelButton);
		cmbDeviceType.setModel(new DefaultComboBoxModel(DeviceType.values()));
		panelButton.add(btnConnect);
		btnConnect.setMnemonic(KeyEvent.VK_V);
		btnAbbrechen.setMnemonic(KeyEvent.VK_A);
		panelButton.add(btnAbbrechen);

	}

}
