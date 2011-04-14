package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import ch.hsr.gymtastic.application.controller.NetworkClientController;
import ch.hsr.gymtastic.application.controller.PresentationClientController;
import ch.hsr.gymtastic.presentation.panels.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.OverviewPanel;


public class Client {

    private JFrame frame;
    private NetworkClientController networkController;
    private PresentationClientController presentationController;
    /**
     * Launch the application.
     */
    public static void newClientFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Client window = new Client();
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
    public Client() {
    	try {
			networkController = new NetworkClientController();
			this.presentationController = new PresentationClientController(networkController);
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
	
	OverviewPanel panelOverview = new OverviewPanel();
	tabbedPane.addTab("Übersicht", null, panelOverview, null);
	
	 
	EvaluationPanel panelEvaluation = new EvaluationPanel(this.presentationController);
	tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
    }

}
