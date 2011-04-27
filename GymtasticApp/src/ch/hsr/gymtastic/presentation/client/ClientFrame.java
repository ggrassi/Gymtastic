package ch.hsr.gymtastic.presentation.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import ch.hsr.gymtastic.application.controller.client.GymCupInfoController;
import ch.hsr.gymtastic.application.controller.client.NetworkClientController;
import ch.hsr.gymtastic.application.controller.client.RoundInfoController;
import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.presentation.panels.client.ActualSquadPanel;
import ch.hsr.gymtastic.presentation.panels.client.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.client.OverviewPanel;

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
	private GymCupInfoController gymCupInfoController;
	private RoundInfoController roundInfoController;

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
			this.gymCupInfoController = new GymCupInfoController();
			this.roundInfoController = new RoundInfoController();
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

		panelOverview = new OverviewPanel(gymCupInfoController, roundInfoController);
		tabbedPane.addTab("�bersicht", null, panelOverview, null);

		panelActualSquad = new ActualSquadPanel(squadController);
		tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);

		panelEvaluation = new EvaluationPanel(this.squadController);
		tabbedPane.addTab("Bewertung", null, panelEvaluation, null);

	}

}
