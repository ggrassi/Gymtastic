package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.server.application.controller.GymCupController;

public class SquadSelectionTableModel extends AbstractTableModel implements
		Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Riege" };
	private final GymCupController gymCupController;

	public SquadSelectionTableModel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (gymCupController.getGymCup() != null) {
			if (gymCupController.getGymCup().getUnallocatedSquads() != null) {
				return gymCupController.getGymCup().getUnallocatedSquads()
						.size();
			} else
				return 0;
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return gymCupController.getGymCup().getUnallocatedSquads()
					.get(rowIndex);
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}