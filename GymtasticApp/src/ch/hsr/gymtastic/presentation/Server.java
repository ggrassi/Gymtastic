package ch.hsr.gymtastic.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hsr.gymtastic.application.controller.ClientAllocation;
import ch.hsr.gymtastic.application.controller.RoundAllocation;
import ch.hsr.gymtastic.application.controller.SquadCreator;
import ch.hsr.gymtastic.application.models.DeviceTypeTableModel;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Gymcup;
import ch.hsr.gymtastic.presentation.panels.AthletePanel;
import ch.hsr.gymtastic.presentation.panels.CompetitionPanel;
import ch.hsr.gymtastic.presentation.panels.CupManagementPanel;
import ch.hsr.gymtastic.presentation.panels.DeviceTypePanel;
import ch.hsr.gymtastic.presentation.panels.JudgePanel;
import ch.hsr.gymtastic.presentation.panels.RankingPanel;
import ch.hsr.gymtastic.presentation.panels.RoundAllocationPanel;
import ch.hsr.gymtastic.presentation.panels.SquadPanel;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;
import ch.hsr.gymtastic.technicalServices.utils.ImportStartList;
import ch.hsr.gymtastic.technicalServices.utils.PdfExport;

import com.itextpdf.text.DocumentException;

public class Server {

	private JFrame frameServer;
	private DeviceTypeTableModel deviceTypeTableModel;
	public static ClientAllocation clientAllocation;

	public static Gymcup cup;

	RMIServer rmiServer;

	/**
	 * Launch the application.
	 */
	public static void newServerFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frameServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		try {
			rmiServer = new RMIServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		deviceTypeTableModel = new DeviceTypeTableModel(rmiServer);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameServer = new JFrame();
		frameServer.setBounds(100, 100, 638, 474);
		frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameServer.setExtendedState(Frame.MAXIMIZED_BOTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frameServer.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		CupManagementPanel panelGymCup = new CupManagementPanel();
		tabbedPane.addTab("Cupverwaltung", null, panelGymCup, null);

		CompetitionPanel panelCompetition = new CompetitionPanel();
		tabbedPane.addTab("Wettkampfverwaltung", null, panelCompetition, null);

		SquadPanel panelSquad = new SquadPanel();
		tabbedPane.addTab("Riege", null, panelSquad, null);

		AthletePanel panelAthlete = new AthletePanel();
		tabbedPane.addTab("Athleten", null, panelAthlete, null);

		JudgePanel panelJudge = new JudgePanel();
		tabbedPane.addTab("Kampfrichter", null, panelJudge, null);

		DeviceTypePanel panelDeviceType = new DeviceTypePanel(
				deviceTypeTableModel, rmiServer);
		tabbedPane.addTab("Ger\u00E4tezuweisung", null, panelDeviceType, null);

		RoundAllocationPanel RoundAllocatoin = new RoundAllocationPanel(
				clientAllocation);
		tabbedPane.addTab("Durchgangssteuerung", null, RoundAllocatoin, null);

		RankingPanel panelRanking = new RankingPanel();
		tabbedPane.addTab("Ranglistengenerierung", null, panelRanking, null);

		JPanel panelLogo = new JPanel();
		frameServer.getContentPane().add(panelLogo, BorderLayout.NORTH);

		JPanel panelStatus = new JPanel();
		frameServer.getContentPane().add(panelStatus, BorderLayout.SOUTH);
	}

	public static void emulateCup() {

		/* create a cup */
		System.out.println("******** Cup *************");
		cup = new Gymcup("HSR Cup", "Rapperswil");

		/* Import the starter file */
		ImportStartList importList = new ImportStartList(
				"src/ch/hsr/gymtastic/technicalServices/utils/Startliste_Bsp.txt");
		System.out.println("******** Import *************");
		importList.readImport();
		importList.toString();

		/* generate Squads with importlist */
		System.out.println("******** Squad Generator *************");
		SquadCreator squadCreator = new SquadCreator(importList);
		squadCreator.insertImportToDB();
		cup.importAllSquads();
		// brauchts nur für Review damit Startliste generiert wird
		// cup.addSquads(squadCreator.createSquads());

		/* create a competition in the cup */
		System.out.println("******** Competition *************");
		Competition competition1 = new Competition("Wettkampf1",
				new GregorianCalendar(2011, 04, 04, 16, 0, 0),
				"Wettkampf Programm 1");
		cup.addCompetition(competition1);

		/* Riegeneinteilung - die ersten 6 Riegen zum Wettkampf1 hinzuf�gen */
		System.out.println("******** Add squads to Competition *************");
		competition1.addSquad(cup.getSquad(1));
		competition1.addSquad(cup.getSquad(2));
		competition1.addSquad(cup.getSquad(3));
		competition1.addSquad(cup.getSquad(4));
		competition1.addSquad(cup.getSquad(5));
		competition1.addSquad(cup.getSquad(6));

		/* create the round allocation for the competition */
		RoundAllocation ra = new RoundAllocation(competition1.getSquads());
		competition1.addRoundAllocation(ra);

		// System.out.println("******** Round Allocation Generator *************");
		// RoundAllocation ra = new RoundAllocation(squads);
		// System.out.println("Riege vor der Rotation");
		// System.out.println(ra.getRoundAllocation(0));
		// System.out.println("Riege nach der Rotation");
		// System.out.println(ra.roundChange(ra.getRoundAllocation(0)));
		// //

		/* create a startlist pdf */
		PdfExport export = new PdfExport(cup.getSquads());
		try {
			export
					.createStartList("src/ch/hsr/gymtastic/technicalServices/utils/Startliste.pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}