package ch.hsr.gymtastic.presentation.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hsr.gymtastic.application.controller.client.GymCupInfoController;
import ch.hsr.gymtastic.application.controller.client.NetworkClientController;
import ch.hsr.gymtastic.application.controller.client.RoundInfoController;
import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.application.models.ClientModel;
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
	private GymCupInfoController gymCupInfoController;
	private RoundInfoController roundInfoController;
	private ClientModel clientModel;

	/**
	 * Launch the application.
	 * 
	 * @param networkController
	 */
	public static void newClientFrame(
			final NetworkClientController networkController,
			final ClientModel clientModel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame(networkController,
							clientModel);
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
			ClientModel clientModel) {
		try {
			this.networkController = networkController;
			this.clientModel = clientModel;
			squadController = clientModel.getSquadController();
			networkController.setSquadController(squadController);
			gymCupInfoController = new GymCupInfoController();
			networkController.setGymCupInfoController(gymCupInfoController);
			roundInfoController = new RoundInfoController();
			networkController.setRoundInfoController(roundInfoController);

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
		frame.setTitle("Gymtastic Client");

		panelStatus = new JPanel();
		frame.getContentPane().add(panelStatus, BorderLayout.SOUTH);

		panelLogo = new JPanel();
		frame.getContentPane().add(panelLogo, BorderLayout.NORTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelOverview = new OverviewPanel(clientModel, gymCupInfoController,
				roundInfoController);
		tabbedPane.addTab("�bersicht", null, panelOverview, null);

		panelActualSquad = new ActualSquadPanel(clientModel);
		tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);

		panelEvaluation = new EvaluationPanel(squadController, clientModel);
		tabbedPane.addTab("Bewertung", null, panelEvaluation, null);

		// TO BE DELETED--------------------------
		// tabbedPane.setEnabledAt(1, false);
		// tabbedPane.setEnabledAt(2, false);
	}

}
