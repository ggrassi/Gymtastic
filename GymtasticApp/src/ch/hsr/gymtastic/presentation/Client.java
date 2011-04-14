package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import ch.hsr.gymtastic.presentation.panels.EvaluationPanel;
import ch.hsr.gymtastic.presentation.panels.OverviewPanel;
import java.awt.GridBagLayout;

public class Client {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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
	
	JPanel panelEvaluation = new JPanel();
	tabbedPane.addTab("Bewertung", null, panelEvaluation, null);
    }

}
