package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class Client {

    private JFrame frameClient;
    private JTable tableActualSquad;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Client window = new Client();
		    window.frameClient.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Client() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frameClient = new JFrame();
	frameClient.setBounds(100, 100, 645, 424);
	frameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameClient.getContentPane().setLayout(new BorderLayout(0, 0));
	
	JPanel panelStatus = new JPanel();
	frameClient.getContentPane().add(panelStatus, BorderLayout.SOUTH);
	
	JPanel panelLogo = new JPanel();
	frameClient.getContentPane().add(panelLogo, BorderLayout.NORTH);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frameClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
	JPanel panelActualSquad = new JPanel();
	tabbedPane.addTab("Aktuelle Riege", null, panelActualSquad, null);
	panelActualSquad.setLayout(new BorderLayout(0, 0));
	
	JPanel panelSquadInformation = new JPanel();
	panelActualSquad.add(panelSquadInformation, BorderLayout.NORTH);
	panelSquadInformation.setLayout(new BoxLayout(panelSquadInformation, BoxLayout.X_AXIS));
	
	JLabel lblNewLabel = new JLabel("New label");
	panelSquadInformation.add(lblNewLabel);
	
	tableActualSquad = new JTable();
	panelActualSquad.add(tableActualSquad, BorderLayout.CENTER);
	
	JPanel panelControl = new JPanel();
	panelActualSquad.add(panelControl, BorderLayout.SOUTH);
    }

}