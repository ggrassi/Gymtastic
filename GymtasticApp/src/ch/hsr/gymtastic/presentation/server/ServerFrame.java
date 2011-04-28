package ch.hsr.gymtastic.presentation.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hsr.gymtastic.application.controller.server.ClientAllocator;
import ch.hsr.gymtastic.application.controller.server.NetworkServerController;
import ch.hsr.gymtastic.application.controller.server.RoundAllocator;
import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.application.models.DeviceTypeTableModel;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.presentation.panels.server.AthletePanel;
import ch.hsr.gymtastic.presentation.panels.server.CompetitionPanel;
import ch.hsr.gymtastic.presentation.panels.server.CupManagementPanel;
import ch.hsr.gymtastic.presentation.panels.server.DeviceTypePanel;
import ch.hsr.gymtastic.presentation.panels.server.JudgePanel;
import ch.hsr.gymtastic.presentation.panels.server.RankingPanel;
import ch.hsr.gymtastic.presentation.panels.server.RoundAllocationPanel;
import ch.hsr.gymtastic.technicalServices.utils.PdfExport;

import com.itextpdf.text.DocumentException;

public class ServerFrame {

    private JFrame frameServer;
    private DeviceTypeTableModel deviceTypeTableModel;
    public static ClientAllocator clientAllocation;

    public static GymCup cup;

    private NetworkServerController networkController;
    private CupManagementPanel panelGymCup;
    private CupManagementModel cupManagementModel;
    private CompetitionModel competitionModel;

    /**
     * Create the application.
     * 
     * @param networkServerController
     */
    public ServerFrame(NetworkServerController networkServerController, CupManagementModel cupManagementModel, CompetitionModel competitionModel) {
	networkController = networkServerController;
	this.cupManagementModel = cupManagementModel;
	deviceTypeTableModel = new DeviceTypeTableModel(networkController);
	initialize();
	invokeFrame();
    }

    public JFrame getFrameServer() {
	return frameServer;
    }

    public ServerFrame() {
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

	frameServer = new JFrame();
	frameServer.setBounds(100, 100, 638, 474);
	frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameServer.setTitle("Gymtastic Server");
//	frameServer.setExtendedState(Frame.MAXIMIZED_BOTH);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frameServer.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	panelGymCup = new CupManagementPanel(cupManagementModel);
	tabbedPane.addTab("Cupverwaltung", null, panelGymCup, null);

	CompetitionPanel panelCompetition = new CompetitionPanel(competitionModel);
	tabbedPane.addTab("Wettkampfverwaltung", null, panelCompetition, null);

	AthletePanel panelAthlete = new AthletePanel();
	tabbedPane.addTab("Athleten", null, panelAthlete, null);

	JudgePanel panelJudge = new JudgePanel();
	tabbedPane.addTab("Kampfrichter", null, panelJudge, null);

	DeviceTypePanel panelDeviceType = new DeviceTypePanel(deviceTypeTableModel, networkController);
	tabbedPane.addTab("Ger\u00E4tezuweisung", null, panelDeviceType, null);

	RoundAllocationPanel roundAllocation = null;
	try {
	    roundAllocation = new RoundAllocationPanel(clientAllocation, networkController);
	} catch (ConnectException e) {
	    e.printStackTrace();
	}
	tabbedPane.addTab("Durchgangssteuerung", null, roundAllocation, null);

	RankingPanel panelRanking = new RankingPanel();
	tabbedPane.addTab("Ranglistengenerierung", null, panelRanking, null);

	JPanel panelLogo = new JPanel();
	frameServer.getContentPane().add(panelLogo, BorderLayout.NORTH);

	JPanel panelStatus = new JPanel();
	frameServer.getContentPane().add(panelStatus, BorderLayout.SOUTH);
    }

    private void invokeFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
//		    ServerFrame window = new ServerFrame();
//		    window.frameServer.setVisible(true);
		    frameServer.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public static void emulateCup() {

	/* create a cup */
	System.out.println("******** Cup *************");
//	cup = new GymCup("HSR Cup", "Rapperswil");

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
//	System.out.println("******** Competition *************");
//	Competition competition1 = new Competition("Wettkampf1", new GregorianCalendar(2011, 04, 04, 16, 0, 0),
//		"Wettkampf Programm 1");
//	cup.addCompetition(competition1);

	/* Riegeneinteilung - die ersten 6 Riegen zum Wettkampf1 hinzuf�gen */
//	System.out.println("******** Add squads to Competition *************");
//	competition1.addSquad(cup.getSquad(1));
//	competition1.addSquad(cup.getSquad(2));
//	competition1.addSquad(cup.getSquad(3));
//	competition1.addSquad(cup.getSquad(4));
//	competition1.addSquad(cup.getSquad(5));
//	competition1.addSquad(cup.getSquad(6));
//
//	/* create the round allocation for the competition */
//	RoundAllocation ra = new RoundAllocation(competition1.getSquads());
//	competition1.addRoundAllocation(ra);

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
	    export.createStartList("src/ch/hsr/gymtastic/technicalServices/utils/Startliste.pdf");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }
}