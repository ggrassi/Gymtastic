package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Competition;

/**
 * The Class SquadsInCompetitionTableModel defines the behavior of the table.
 */
public class SquadsInCompetitionTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Riege" };
	private Competition competition;

	/**
	 * Instantiates a new squads in competition table model.
	 */
	public SquadsInCompetitionTableModel() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if (competition != null) {
			return competition.getSquads().size();
		}
		return 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (competition != null) {
			switch (columnIndex) {
			case 0:
				return competition.getSquads().get(rowIndex);
			}
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();

	}

	/**
	 * Sets the competition.
	 * 
	 * @param competition
	 *            the new competition
	 */
	public void setCompetition(Competition competition) {
		if (competition != null) {
			this.competition = competition;
			competition.addObserver(this);
		}
		fireTableDataChanged();
	}

}
