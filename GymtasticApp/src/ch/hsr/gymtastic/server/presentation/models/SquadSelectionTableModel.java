package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.server.application.controller.GymCupController;

/**
 * The Class SquadSelectionTableModel defines the behavior of the table
 */
public class SquadSelectionTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private String[] columns = { "Riege" };
    private final GymCupController gymCupController;

    /**
     * Instantiates a new squad selection table model.
     * 
     * @param gymCupController
     *            the gym cup controller
     */
    public SquadSelectionTableModel(GymCupController gymCupController) {
	this.gymCupController = gymCupController;
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
	if (gymCupController != null) {
	    if (gymCupController.getGymCup() != null) {
		if (gymCupController.getGymCup().getUnallocatedSquads() != null) {
		    return gymCupController.getGymCup().getUnallocatedSquads().size();
		}
	    }
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
	switch (columnIndex) {
	case 0:
	    return gymCupController.getGymCup().getUnallocatedSquads().get(rowIndex);
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

}