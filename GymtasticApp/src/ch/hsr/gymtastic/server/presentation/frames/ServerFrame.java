package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.panels.AthletePanel;
import ch.hsr.gymtastic.server.presentation.panels.CompetitionPanel;
import ch.hsr.gymtastic.server.presentation.panels.CupManagementPanel;
import ch.hsr.gymtastic.server.presentation.panels.DeviceTypePanel;
import ch.hsr.gymtastic.server.presentation.panels.RankingPanel;
import ch.hsr.gymtastic.server.presentation.panels.RoundAllocationPanel;

public class ServerFrame implements Observer {

	private JFrame frameServer;
	private CupManagementPanel panelGymCup;
	private GymCupController gymCupController;
	private CompetitionPanel panelCompetition;
	private AthletePanel panelAthlete;
	private DeviceTypePanel panelDeviceType;
	private RankingPanel panelRanking;
	private JPanel panelLogo;
	private JPanel panelStatus;
	private RoundAllocationPanel roundAllocation;
	private JTabbedPane tabbedPaneServer;

	/**
	 * Create the application.
	 * 
	 * @param networkServerController
	 */

	public static void main(String[] args) {
		setLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();
					window.frameServer.setVisible(true);
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

	public ServerFrame() {
		this.gymCupController = new GymCupController();
		this.gymCupController.addObserver(this);
		initialize();
	}

	public JFrame getFrameServer() {
		return frameServer;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frameServer = new JFrame();
		frameServer.setBounds(100, 100, 638, 474);
		frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameServer.setTitle("Gymtastic Server");

		tabbedPaneServer = new JTabbedPane(JTabbedPane.TOP);
		frameServer.getContentPane().add(tabbedPaneServer, BorderLayout.CENTER);

		panelGymCup = new CupManagementPanel(gymCupController);
		tabbedPaneServer.addTab("Cupverwaltung", null, panelGymCup, null);
		panelDeviceType = new DeviceTypePanel(gymCupController);
		tabbedPaneServer.addTab("Ger\u00E4tezuweisung", null, panelDeviceType,
				null);
		panelLogo = new JPanel();
		frameServer.getContentPane().add(panelLogo, BorderLayout.NORTH);
		panelStatus = new JPanel();
		frameServer.getContentPane().add(panelStatus, BorderLayout.SOUTH);

	}

	private void createPanels() {
		if (panelCompetition == null) {

			panelCompetition = new CompetitionPanel(gymCupController);
			panelRanking = new RankingPanel(gymCupController);
			panelAthlete = new AthletePanel(gymCupController);
			roundAllocation = new RoundAllocationPanel(gymCupController);
			tabbedPaneServer.addTab("Wettkampfverwaltung", null,
					panelCompetition, null);
			tabbedPaneServer.addTab("Durchgangssteuerung", null,
					roundAllocation, null);
			tabbedPaneServer.addTab("Athleten", null, panelAthlete, null);
			tabbedPaneServer.addTab("Ranglistengenerierung", null,
					panelRanking, null);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		createPanels();
	}

}
