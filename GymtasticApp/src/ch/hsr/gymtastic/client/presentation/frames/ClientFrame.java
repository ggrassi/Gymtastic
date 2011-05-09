package ch.hsr.gymtastic.client.presentation.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hsr.gymtastic.client.application.controller.CompetitionInfoController;
import ch.hsr.gymtastic.client.application.controller.GymCupInfoController;
import ch.hsr.gymtastic.client.application.controller.NetworkClientController;
import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.client.presentation.frames.panels.ActualSquadPanel;
import ch.hsr.gymtastic.client.presentation.frames.panels.EvaluationPanel;
import ch.hsr.gymtastic.client.presentation.frames.panels.OverviewPanel;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

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
				roundInfoController, squadController, deviceType, this);
		tabbedPane.addTab("\u00dcbersicht", null, panelOverview, null);

	}

	public void setActualSquad(Squad actualSquad) {
		this.actualSquad = actualSquad;
		createPanels();

	}

	public void createPanels() {
		if (panelActualSquad == null && panelEvaluation == null) {
			panelEvaluation = new EvaluationPanel(squadController, deviceType, this);
			tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
			panelActualSquad = new ActualSquadPanel(actualSquad, deviceType);
			tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);
		}

	}

	public void setFocusOnPanel(int panelIndex) {
		if (tabbedPane.getComponentAt(panelIndex) != null) {
			tabbedPane.getModel().setSelectedIndex(panelIndex);
		}

	}

	public Squad getActualSquad() {
		return actualSquad;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		actualSquad = squadController.getSquad();

	}

}
