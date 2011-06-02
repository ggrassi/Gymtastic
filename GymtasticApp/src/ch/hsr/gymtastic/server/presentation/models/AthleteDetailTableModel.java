package ch.hsr.gymtastic.server.presentation.models;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.GymCupController;
import ch.hsr.gymtastic.server.application.controller.persistence.DBController;

/**
 * The Class AthleteDetailTableModel defines the behavior
 */
public class AthleteDetailTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Ger\u00e4t", "D-Note", "E-Note 1",
			"E-Note 2", "E-Note 3", "Bonus", "Penalty", "Gesamtnote" };
	private Athlete athlete;
	private GymCupController gymCupController;
	private DecimalFormat finalMarkFormat = new DecimalFormat("#0.00");

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
	private Class[] columnTypes = new Class[] { DeviceType.class, Double.class,
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
					return Double.valueOf(finalMarkFormat.format(mark
							.getFinalMark()));
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
		return !(getColumnClass(col).equals(DeviceType.class) || col == (getColumnCount() - 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	public void setValueAt(final Object value, final int rowIndex,
			final int columnIndex) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (athlete != null) {
					DeviceType deviceType = DeviceType.values()[rowIndex];
					if (deviceType != null) {
						Mark updateMark = athlete.getMark(deviceType);
						switch (columnIndex) {
						case 1:
							updateMark.setdMark((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						case 2:
							updateMark.seteMarkOne((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						case 3:
							updateMark.setemarkTwo((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						case 4:
							updateMark.seteMarkThree((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						case 5:
							updateMark.setBonus((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						case 6:
							updateMark.setPenalty((Double) value);
							DBController.updateMark(updateMark, athlete,
									deviceType);
							return;
						}
					}
				}

			}
		});
		fireTableDataChanged();
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */

	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}

}
