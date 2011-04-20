package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.application.controller.SquadController;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.presentation.panels.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.ActualSquadPanel;
import ch.hsr.gymtastic.presentation.panels.OverviewPanel;

public class ClientFrame {

	private JFrame frame;
	private NetworkClientController networkController;
	private SquadController squadController;
	private JPanel panelStatus;
	private JPanel panelLogo;
	private JTabbedPane tabbedPane;
	private ActualSquadPanel panelActualSquad;
	private EvaluationPanel panelEvaluation;
	private OverviewPanel panelOverview;
	private DeviceType deviceType;

	/**
	 * Launch the application.
	 * 
	 * @param networkController
	 */
	public static void newClientFrame(
			final NetworkClientController networkController,
			final DeviceType deviceType) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame(networkController,
							deviceType);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame(NetworkClientController networkController,
			DeviceType deviceType) {
		try {
			this.squadController = new SquadController();
			this.networkController = networkController;
			this.networkController.setSquadController(squadController);
			this.deviceType = deviceType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelStatus = new JPanel();
		frame.getContentPane().add(panelStatus, BorderLayout.SOUTH);

		panelLogo = new JPanel();
		frame.getContentPane().add(panelLogo, BorderLayout.NORTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelOverview = new OverviewPanel();
		tabbedPane.addTab("†bersicht", null, panelOverview, null);

		panelActualSquad = new ActualSquadPanel(squadController);
		tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);

		panelEvaluation = new EvaluationPanel(this.squadController);
		tabbedPane.addTab("Bewertung", null, panelEvaluation, null);

		// TO BE DELETED--------------------------
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
	}

}
