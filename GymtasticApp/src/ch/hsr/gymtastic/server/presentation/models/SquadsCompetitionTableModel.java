package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Competition;

public class SquadsCompetitionTableModel extends AbstractTableModel implements
		Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Riege" };
	private Competition competition;

	public SquadsCompetitionTableModel() {
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
		if (competition != null) {
			return competition.getSquads().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return competition.getSquads().get(rowIndex);
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
		this.competition.addObserver(this);
		fireTableDataChanged();
	}

}