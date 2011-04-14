package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.application.models.ActualSquadTableModel;
import ch.hsr.gymtastic.technicalServices.network.RMIClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.ConnectException;
import java.rmi.RemoteException;

public class Client {

	private JFrame frameClient;
	private JTable tableActualSquad;
	private NetworkClientController networkController;
	private ActualSquadTableModel actualSquadTableModel;
	private JPanel panelStatus;
	private JPanel panelLogo;
	private JTabbedPane tabbedPane;
	private JPanel panelActualSquad;
	private JPanel panelSquadInformation;
	private JLabel lblTextSquadName;
	private JLabel lblSquadName;
	private JLabel lblTextNumberOfAthletes;
	private JLabel lblNumberOfAthletes;
	private JPanel panelControl;
	private JScrollPane scrollPaneTableActualSquad;
	private JButton btn;

	/**
	 * Launch the application.
	 */
	public static void newClientFrame(final NetworkClientController networkController) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client(networkController);
					window.frameClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public Client(NetworkClientController networkController) {
		this.networkController = networkController;
		actualSquadTableModel = new ActualSquadTableModel(networkController);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameClient = new JFrame();
		frameClient.setTitle("Gymtastic - Client");
		frameClient.setBounds(100, 100, 645, 424);
		frameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameClient.getContentPane().setLayout(new BorderLayout(0, 0));
		panelStatus = new JPanel();

		frameClient.getContentPane().add(panelStatus, BorderLayout.SOUTH);

		panelLogo = new JPanel();
		frameClient.getContentPane().add(panelLogo, BorderLayout.NORTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frameClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelActualSquad = new JPanel();
		tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);
		panelActualSquad.setLayout(new BorderLayout(0, 0));

		panelSquadInformation = new JPanel();
		panelActualSquad.add(panelSquadInformation, BorderLayout.NORTH);
		panelSquadInformation.setLayout(new BoxLayout(panelSquadInformation,
				BoxLayout.X_AXIS));

		lblTextSquadName = new JLabel("Riegen Name: ");
		panelSquadInformation.add(lblTextSquadName);

		lblSquadName = new JLabel(" ");
		panelSquadInformation.add(lblSquadName);

		lblTextNumberOfAthletes = new JLabel("Anzahl Athleten: ");
		panelSquadInformation.add(lblTextNumberOfAthletes);

		lblNumberOfAthletes = new JLabel("0");
		panelSquadInformation.add(lblNumberOfAthletes);

		panelControl = new JPanel();
		panelActualSquad.add(panelControl, BorderLayout.SOUTH);
		panelControl.setLayout(new BorderLayout(0, 0));

		btn = new JButton("Durchgang Abschliessen");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						networkController.updateServer();
					} catch (ConnectException e) {
						e.printStackTrace();
					}

			}
		});
		panelControl.add(btn, BorderLayout.EAST);

		scrollPaneTableActualSquad = new JScrollPane();
		panelActualSquad.add(scrollPaneTableActualSquad, BorderLayout.CENTER);

		tableActualSquad = new JTable();
		tableActualSquad.setModel(actualSquadTableModel);
		scrollPaneTableActualSquad.setViewportView(tableActualSquad);
	}

}
