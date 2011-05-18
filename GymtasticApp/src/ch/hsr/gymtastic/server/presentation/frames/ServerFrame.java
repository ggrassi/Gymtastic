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
import ch.hsr.gymtastic.server.presentation.frames.panels.AthletePanel;
import ch.hsr.gymtastic.server.presentation.frames.panels.CompetitionPanel;
import ch.hsr.gymtastic.server.presentation.frames.panels.CupManagementPanel;
import ch.hsr.gymtastic.server.presentation.frames.panels.DeviceTypePanel;
import ch.hsr.gymtastic.server.presentation.frames.panels.RankingPanel;
import ch.hsr.gymtastic.server.presentation.frames.panels.RoundAllocationPanel;

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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
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

	public ServerFrame() {
		this.gymCupController = new GymCupController();
		this.gymCupController.addObserver(this);
		initialize();
		// invokeFrame();

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
		// frameServer.setExtendedState(Frame.MAXIMIZED_BOTH);

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

	private void invokeFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ServerFrame window = new ServerFrame();
					// window.frameServer.setVisible(true);
					frameServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * TODO: DELETE emulateCup()
	 */
	public static void emulateCup() {

		/* create a cup */
		// System.out.println("******** Cup *************");
		// cup = new GymCup("HSR Cup", "Rapperswil");

		/* Import the starter file */
		// ImportStartList importList = new ImportStartList(
		// "src/ch/hsr/gymtastic/technicalServices/utils/Startliste_Bsp.txt");
		// System.out.println("******** Import *************");
		// importList.readImport();
		// importList.toString();

		// /* generate Squads with importlist */
		// System.out.println("******** Squad Generator *************");
		// SquadCreator squadCreator = new SquadCreator(importList);
		// squadCreator.insertImportToDB();
		// cup.importAllSquads();
		// // brauchts nur für Review damit Startliste generiert wird
		// cup.addSquads(squadCreator.createSquads());

		/* create a competition in the cup */
		// System.out.println("******** Competition *************");
		// Competition competition1 = new Competition("Wettkampf1", new
		// GregorianCalendar(2011, 04, 04, 16, 0, 0),
		// "Wettkampf Programm 1");
		// cup.addCompetition(competition1);

		/* Riegeneinteilung - die ersten 6 Riegen zum Wettkampf1 hinzuf�gen */
		// System.out.println("******** Add squads to Competition *************");
		// competition1.addSquad(cup.getSquad(1));
		// competition1.addSquad(cup.getSquad(2));
		// competition1.addSquad(cup.getSquad(3));
		// competition1.addSquad(cup.getSquad(4));
		// competition1.addSquad(cup.getSquad(5));
		// competition1.addSquad(cup.getSquad(6));
		//
		// /* create the round allocation for the competition */
		// RoundAllocation ra = new RoundAllocation(competition1.getSquads());
		// competition1.addRoundAllocation(ra);

		// System.out.println("******** Round Allocation Generator *************");
		// RoundAllocation ra = new RoundAllocation(squads);
		// System.out.println("Riege vor der Rotation");
		// System.out.println(ra.getRoundAllocation(0));
		// System.out.println("Riege nach der Rotation");
		// System.out.println(ra.roundChange(ra.getRoundAllocation(0)));
		// //

		/* create a startlist pdf */
		// PdfExport export = new PdfExport(cup.getSquads());
		// try {
		// export
		// .createStartList("src/ch/hsr/gymtastic/technicalServices/utils/Startliste.pdf");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// e.printStackTrace();
		// }
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