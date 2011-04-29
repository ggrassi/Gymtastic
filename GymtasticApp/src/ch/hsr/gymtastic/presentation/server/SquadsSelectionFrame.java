package ch.hsr.gymtastic.presentation.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.SquadSelectionListModel;
import ch.hsr.gymtastic.application.models.SquadSelectionTableModel;

public class SquadsSelectionFrame {

    private JFrame frame;
    private CompetitionModel competitionModel;
    private int index;
    private JTable tableSquads;
    private SquadSelectionTableModel squadSelectionTableModel;

    /**
     * Create the application.
     */
    public SquadsSelectionFrame(CompetitionModel competitionModel) {
	this.competitionModel = competitionModel;
	initialize();
	invokeFrame();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JScrollPane scrollPaneSquads = new JScrollPane();
	frame.getContentPane().add(scrollPaneSquads, BorderLayout.CENTER);

	tableSquads = new JTable();
	squadSelectionTableModel = new SquadSelectionTableModel(competitionModel);
	tableSquads.setModel(squadSelectionTableModel);
	scrollPaneSquads.setViewportView(tableSquads);

	// würde auch funktionier anstatt dem JTable
	// JList listSquads = new JList();
	// SquadSelectionListModel listModel = new
	// SquadSelectionListModel(competitionModel);
	// listSquads.setModel(listModel);
	// scrollPaneSquads.setViewportView(listSquads);

	JPanel panelSouth = new JPanel();
	frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
	panelSouth.setLayout(new BorderLayout(0, 0));

	JButton btnAddSelectedSquads = new JButton("Selektierte Hinzufügen");
	panelSouth.add(btnAddSelectedSquads, BorderLayout.EAST);
    }

    private void invokeFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    // ServerFrame window = new ServerFrame();
		    // window.frameServer.setVisible(true);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

}
