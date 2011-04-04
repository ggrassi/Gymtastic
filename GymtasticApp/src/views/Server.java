package views;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

import viewModels.DeviceTypeTableModel;

public class Server {

    private JFrame serverFrame;
    private JTable tableDevices;
    DeviceTypeTableModel deviceTypeTableModel = new DeviceTypeTableModel();

    /**
     * Launch the application.
     */
    public static void newServerFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Server window = new Server();
		    window.serverFrame.setVisible(true);
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
	serverFrame = new JFrame();
	serverFrame.setBounds(100, 100, 638, 474);
	serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	serverFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	serverFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
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
	panelDeviceType.setLayout(new BorderLayout(0, 0));
	
	tableDevices = new JTable();
	tableDevices.setModel(deviceTypeTableModel);
	panelDeviceType.add(tableDevices, BorderLayout.CENTER);
	
	JPanel panelControl = new JPanel();
	panelDeviceType.add(panelControl, BorderLayout.SOUTH);
	panelControl.setLayout(new BorderLayout(0, 0));
	
	JButton btnAllocateAllDevices = new JButton("Alle Zuweisen");
	panelControl.add(btnAllocateAllDevices, BorderLayout.EAST);
	
	
	
	JPanel panelRanking = new JPanel();
	tabbedPane.addTab("Ranglistengenerierung", null, panelRanking, null);
	
	JPanel panelLogo = new JPanel();
	serverFrame.getContentPane().add(panelLogo, BorderLayout.NORTH);
	
	JPanel panelStatus = new JPanel();
	serverFrame.getContentPane().add(panelStatus, BorderLayout.SOUTH);
    }

}
