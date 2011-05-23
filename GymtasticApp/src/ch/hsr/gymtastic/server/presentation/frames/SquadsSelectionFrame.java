package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.DBController;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.models.SquadSelectionTableModel;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class SquadsSelectionFrame extends Observable {

	private JFrame squadSelectionFrame;
	private JTable tableSquads;
	private SquadSelectionTableModel squadSelectionTableModel;
	private JButton btnAddSelectedSquads;
	private GymCupController gymCupController;
	private Competition actualCompetition;

	/**
	 * Create the application.
	 * 
	 * @param actualCompetition
	 */

	public SquadsSelectionFrame(GymCupController gymCupController,
			Competition actualCompetition) {
		this.gymCupController = gymCupController;
		this.actualCompetition = actualCompetition;
		initGUI();
		initListeners();
		invokeFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initGUI() {
		squadSelectionFrame = new JFrame();
		squadSelectionFrame.setBounds(100, 100, 450, 300);
		squadSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPaneSquads = new JScrollPane();
		squadSelectionFrame.getContentPane().add(scrollPaneSquads,
				BorderLayout.CENTER);

		tableSquads = new JTable();
		squadSelectionTableModel = new SquadSelectionTableModel(
				gymCupController);
		tableSquads.setModel(squadSelectionTableModel);
		scrollPaneSquads.setViewportView(tableSquads);

		JPanel panelSouth = new JPanel();
		squadSelectionFrame.getContentPane()
				.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		btnAddSelectedSquads = new JButton("Selektierte Hinzuf\u00fcgen");
		panelSouth.add(btnAddSelectedSquads, BorderLayout.EAST);
	}

	private void invokeFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					squadSelectionFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initListeners() {
		btnAddSelectedSquads.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableSquads.getSelectedRows().length > 0) {
					List<Squad> selectedSquads = getSelectedSquads(tableSquads.getSelectedRows());
					gymCupController.getGymCup().addSquadsToCompetition(selectedSquads, actualCompetition);
					DBController.persistCompetition(actualCompetition);
				}
				squadSelectionFrame.dispose();
				updateObservers();
			}

		});

	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	private List<Squad> getSelectedSquads(int[] rows) {
		List<Squad> squads = new ArrayList<Squad>();
		for (int i : rows) {
			int modelRow = tableSquads.convertRowIndexToModel(i);
			Squad selectedSquad = gymCupController.getGymCup()
					.getUnallocatedSquads().get(modelRow);
			squads.add(selectedSquad);
		}
		return squads;
	}

}
