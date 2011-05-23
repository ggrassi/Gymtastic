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

	private JFrame frmClient;
	private JPanel panelStatus;
	private JPanel panelLogo;
	private JTabbedPane tabbedPane;
	private ActualSquadPanel panelActualSquad;
	private EvaluationPanel panelEvaluation;
	private OverviewPanel panelOverview;
	private SquadController squadController;
	private GymCupInfoController gymCupInfoController;
	private CompetitionInfoController competitionInfoController;
	private Squad actualSquad;
	private DeviceType deviceType;
	private final NetworkClientController networkController;

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
					window.frmClient.setVisible(true);
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
		this.networkController = networkController;
		try {
			this.networkController.setSquadController(squadController);
			this.gymCupInfoController = new GymCupInfoController();
			this.gymCupInfoController.addObserver(this);
			this.networkController
					.setGymCupInfoController(gymCupInfoController);
			this.competitionInfoController = new CompetitionInfoController();
			this.networkController
					.setRoundInfoController(competitionInfoController);
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
		frmClient = new JFrame();
		frmClient.setBounds(100, 100, 800, 600);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.setTitle("Gymtastic Client");

		panelStatus = new JPanel();
		frmClient.getContentPane().add(panelStatus, BorderLayout.SOUTH);

		panelLogo = new JPanel();
		frmClient.getContentPane().add(panelLogo, BorderLayout.NORTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelOverview = new OverviewPanel(gymCupInfoController,
				competitionInfoController, squadController, deviceType, this);
		tabbedPane.addTab("\u00dcbersicht", null, panelOverview, null);

	}

	public void setActualSquad(Squad actualSquad) {
		this.actualSquad = actualSquad;
		createPanels();

	}

	public void createPanels() {
		if (tabbedPane.getComponents().length == 1) {
			panelEvaluation = new EvaluationPanel(squadController, deviceType,
					this);
			tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
			panelActualSquad = new ActualSquadPanel(actualSquad, deviceType,
					networkController, this);
			tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);
		}

	}

	public void setFocusOnPanel(int panelIndex) {
		if (tabbedPane.getComponentAt(panelIndex) != null) {
			tabbedPane.getModel().setSelectedIndex(panelIndex);
		}

	}

	public JFrame getFrame() {
		return frmClient;
	}

	public Squad getActualSquad() {
		return actualSquad;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateGymCupInfo();
		updateSquad();

	}

	private void updateGymCupInfo() {
		if (gymCupInfoController.getGymCupClientInfo() != null)
			deviceType = gymCupInfoController.getGymCupClientInfo()
					.getDeviceType();

	}

	private void updateSquad() {
		if (squadController.getSquad() != null)
			actualSquad = squadController.getSquad();
	}

	public void endRound() {
		tabbedPane.remove(panelActualSquad);
		tabbedPane.remove(panelEvaluation);
		panelOverview.waitForNextRound();

	}
	
	public void setOverviewAndActualSquadPanelsDisabled()
	{
		tabbedPane.setEnabledAt(0, false);
		tabbedPane.setEnabledAt(2, false);
	}
	public void setOverviewAndActualSquadPanelsEnabled()
	{
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setEnabledAt(2, true);
	}

}
