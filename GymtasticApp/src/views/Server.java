package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class Server {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void newServerFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Server window = new Server();
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
    public Server() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 638, 474);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
	JPanel panelGymCup = new JPanel();
	tabbedPane.addTab("Cupverwaltung", null, panelGymCup, null);
	
	JPanel panelCompetition = new JPanel();
	tabbedPane.addTab("Wettkampfverwaltung", null, panelCompetition, null);
	
	JPanel panelAthlete = new JPanel();
	tabbedPane.addTab("Athleten", null, panelAthlete, null);
	
	JPanel panelJudge = new JPanel();
	tabbedPane.addTab("Kampfrichter", null, panelJudge, null);
	
	JPanel panelDeviceType = new JPanel();
	tabbedPane.addTab("Ger\u00E4tezuweisung", null, panelDeviceType, null);
	
	JPanel RoundAllocatoin = new JPanel();
	tabbedPane.addTab("Durchgangssteuerung", null, RoundAllocatoin, null);
	
	JPanel panelRanking = new JPanel();
	tabbedPane.addTab("Ranglistengenerierung", null, panelRanking, null);
	
	JPanel panelLogo = new JPanel();
	frame.getContentPane().add(panelLogo, BorderLayout.NORTH);
	
	JPanel panelStatus = new JPanel();
	frame.getContentPane().add(panelStatus, BorderLayout.SOUTH);
    }

}
