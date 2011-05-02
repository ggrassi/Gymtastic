package ch.hsr.gymtastic.presentation.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.SquadSelectionTableModel;
import ch.hsr.gymtastic.domain.Squad;

public class SquadsSelectionFrame {

	private JFrame squadSelectionFrame;
	private CompetitionModel competitionModel;
	private int index;
	private JTable tableSquads;
	private SquadSelectionTableModel squadSelectionTableModel;
	private JButton btnAddSelectedSquads;

	/**
	 * Create the application.
	 */
	public SquadsSelectionFrame(CompetitionModel competitionModel) {
		this.competitionModel = competitionModel;
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
				competitionModel);
		tableSquads.setModel(squadSelectionTableModel);
		scrollPaneSquads.setViewportView(tableSquads);

		// würde auch funktionier anstatt dem JTable
		// JList listSquads = new JList();
		// SquadSelectionListModel listModel = new
		// SquadSelectionListModel(competitionModel);
		// listSquads.setModel(listModel);
		// scrollPaneSquads.setViewportView(listSquads);

		JPanel panelSouth = new JPanel();
		squadSelectionFrame.getContentPane()
				.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		btnAddSelectedSquads = new JButton("Selektierte Hinzufügen");
		panelSouth.add(btnAddSelectedSquads, BorderLayout.EAST);
	}

	private void invokeFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ServerFrame window = new ServerFrame();
					// window.frameServer.setVisible(true);
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
				List<Squad> selectedSquads = new ArrayList<Squad>();
				if (tableSquads.getSelectedRows().length > 0) {
					int[] rows = tableSquads.getSelectedRows();
					for (int i : rows) {
						int modelRow = tableSquads.convertRowIndexToModel(i);
						selectedSquads.add(competitionModel.getGymCup().getSquadsUnallocated().get(modelRow));
					}
					for(Squad s: selectedSquads){
						competitionModel.addSquadToCompetition(s, competitionModel.getActualCompetition());
					}
					squadSelectionFrame.dispose();
				}

			}
		});

	}

}
