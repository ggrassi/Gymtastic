package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.application.controller.SquadController;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.presentation.panels.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.OverviewPanel;


public class ClientFrame implements Observer{

    private JFrame frame;
    private NetworkClientController networkController;
    private SquadController squadController;
    /**
     * Launch the application.
     * @param networkController 
     */
    public static void newClientFrame(final NetworkClientController networkController) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    ClientFrame window = new ClientFrame(networkController);
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
    public ClientFrame(NetworkClientController networkController) {
    	try {
    		this.squadController = new SquadController();
			this.networkController = networkController;
			this.networkController.setSquadController(squadController);
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
	
	JPanel panelStatus = new JPanel();
	frame.getContentPane().add(panelStatus, BorderLayout.SOUTH);
	
	JPanel panelLogo = new JPanel();
	frame.getContentPane().add(panelLogo, BorderLayout.NORTH);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
	OverviewPanel panelOverview = new OverviewPanel(squadController);
	tabbedPane.addTab("Übersicht", null, panelOverview, null);
	
	 
	EvaluationPanel panelEvaluation = new EvaluationPanel(this.squadController);
	tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
    }

	@Override
	public void update(Observable o, Object object) {
		squadController.setSquad((Squad) object);
	}

}
