package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.server.application.controller.GymCupController;

/**
 * The Class AthleteDetailTableModel defines the behavior
 */
public class AthleteDetailTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Gerät", "D-Note", "E-Note 1", "E-Note 2",
			"E-Note 3", "Bonus", "Penalty", "Gesamtnote" };
	private Athlete athlete;
	private GymCupController gymCupController;

	/**
	 * Instantiates a new athlete detail table model.
	 * 
	 * @param athlete
	 *            the athlete
	 * @param gymCupController
	 *            the gym cup controller
	 */
	public AthleteDetailTableModel(Athlete athlete,
			GymCupController gymCupController) {
		this.athlete = athlete;
		this.gymCupController = gymCupController;
		this.gymCupController.getCompetitionController().addObserver(this);

	}

	/** The column types. */
	
	@SuppressWarnings("unchecked")
	Class[] columnTypes = new Class[] { DeviceType.class, Double.class,
			Double.class, Double.class, Double.class, Double.class,
			Double.class, Double.class };

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@SuppressWarnings( { "unchecked" })
	public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
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
		if (athlete != null) {
			return DeviceType.values().length;
		} else
			return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (athlete != null) {
			DeviceType deviceType = DeviceType.values()[rowIndex];
			if (deviceType != null) {
				Mark mark = athlete.getMark(deviceType);
				switch (columnIndex) {
				case 0:
					return deviceType;
				case 1:
					return mark.getdMark();
				case 2:
					return mark.geteMarkOne();
				case 3:
					return mark.getEmarkTwo();
				case 4:
					return mark.geteMarkThree();
				case 5:
					return mark.getBonus();
				case 6:
					return mark.getPenalty();
				case 7:
					return mark.getFinalMark();
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int col) {
		if (getColumnClass(col).equals(DeviceType.class)
				|| col == (getColumnCount() - 1)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (athlete != null) {
			DeviceType deviceType = DeviceType.values()[rowIndex];
			if (deviceType != null) {
				Mark mark = athlete.getMark(deviceType);
				switch (columnIndex) {
				case 1:
					mark.setdMark((Double) value);
					return;
				case 2:
					mark.seteMarkOne((Double) value);
					return;
				case 3:
					mark.setEmarkTwo((Double) value);
					return;
				case 4:
					mark.seteMarkThree((Double) value);
					return;
				case 5:
					mark.setBonus((Double) value);
					return;
				case 6:
					mark.setPenalty((Double) value);
					return;
				}
			}
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/**
	 * Sets the athlete which gets shown
	 * 
	 * @param athlete
	 *            the new athlete
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		// fireTableDataChanged();
	}

}
