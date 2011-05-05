package ch.hsr.gymtastic.presentation.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hsr.gymtastic.application.controller.client.CompetitionInfoController;
import ch.hsr.gymtastic.application.controller.client.GymCupInfoController;
import ch.hsr.gymtastic.application.controller.client.NetworkClientController;
import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.presentation.panels.client.ActualSquadPanel;
import ch.hsr.gymtastic.presentation.panels.client.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.client.OverviewPanel;

public class ClientFrame implements Observer {

	private JFrame frame;
	private JPanel panelStatus;
	private JPanel panelLogo;
	private JTabbedPane tabbedPane;
	private ActualSquadPanel panelActualSquad;
	private EvaluationPanel panelEvaluation;
	private OverviewPanel panelOverview;
	private SquadController squadController;
	private GymCupInfoController gymCupInfoController;
	private CompetitionInfoController roundInfoController;
	private Squad actualSquad;
	private DeviceType deviceType;

	/**
	 * Launch the application.
	 * 
	 * @param networkController
	 */
	public static void newClientFrame(final SquadController squadController,
			final NetworkClientController networkController) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame(squadController,
							networkController);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param squadController
	 */
	public ClientFrame(SquadController squadController,
			NetworkClientController networkController) {
		this.squadController = squadController;
		try {
			networkController.setSquadController(squadController);
			gymCupInfoController = new GymCupInfoController();
			networkController.setGymCupInfoController(gymCupInfoController);
			roundInfoController = new CompetitionInfoController();
			networkController.setRoundInfoController(roundInfoController);
			this.squadController.addObserver(this);

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

		panelOverview = new OverviewPanel(gymCupInfoController,
				roundInfoController, squadController, deviceType);
		tabbedPane.addTab("�bersicht", null, panelOverview, null);

	}

	public void setActualSquad(Squad actualSquad) {
		this.actualSquad = actualSquad;
		createPanels();

	}

	private void createPanels() {
		if (panelActualSquad == null && panelEvaluation == null) {
			panelActualSquad = new ActualSquadPanel(actualSquad, deviceType);
			tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);
			panelEvaluation = new EvaluationPanel(squadController, deviceType);
			tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
		}

	}

	public Squad getActualSquad() {
		return actualSquad;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		actualSquad = squadController.getSquad();
		createPanels();

	}

}
