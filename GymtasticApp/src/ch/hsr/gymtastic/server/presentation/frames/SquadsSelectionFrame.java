package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.GymCupController;
import ch.hsr.gymtastic.server.application.controller.persistence.DBController;
import ch.hsr.gymtastic.server.presentation.models.SquadSelectionTableModel;

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
		squadSelectionFrame.setBounds(100, 100, 168, 200);
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
		panelSouth.add(btnAddSelectedSquads, BorderLayout.CENTER);
		btnAddSelectedSquads.setEnabled(false);
	}

	/**
	 * Initialize the listeners
	 */
	private void initListeners() {
		btnAddSelectedSquads.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableSquads.getSelectedRowCount() > 0) {
					List<Squad> selectedSquads = getSelectedSquads(tableSquads
							.getSelectedRows());
					gymCupController.getGymCup().addSquadsToCompetition(
							selectedSquads, actualCompetition);
					DBController.saveCompetition(actualCompetition);
				}
				updateObservers();
				squadSelectionFrame.dispose();
			}

		});

		tableSquads.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (tableSquads.getSelectedRowCount() > 0) {
							if (actualCompetition.getSquads().size()
									+ tableSquads.getSelectedRowCount() > 6) {
								btnAddSelectedSquads.setEnabled(false);
							} else {
								btnAddSelectedSquads.setEnabled(true);
							}
						}
					}
				});

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

	/**
	 * invoke Frame runs a threat which shows a frame
	 */
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

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

}
