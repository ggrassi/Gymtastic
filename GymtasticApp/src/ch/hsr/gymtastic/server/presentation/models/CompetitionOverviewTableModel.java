package ch.hsr.gymtastic.server.presentation.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

/**
 * The Class CompetitionOverviewTableModel defines the behavior of the Table
 */
public class CompetitionOverviewTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private String[] columns = { "Beschreibung", "Datum" };
    private GymCupController gymCupController;

    /**
     * Instantiates a new competition overview table model.
     * 
     * @param gymCupController
     *            the gym cup controller
     */
    public CompetitionOverviewTableModel(GymCupController gymCupController) {
	this.gymCupController = gymCupController;
	this.gymCupController.getGymCup().addObserver(this);
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
	if (gymCupController.getGymCup() != null) {
	    if (gymCupController.getGymCup().getCompetitions() != null) {
		return gymCupController.getGymCup().getCompetitions().size();
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
	Competition competition = gymCupController.getGymCup().getCompetitions().get(rowIndex);
	switch (columnIndex) {
	case 0:
	    return competition.getDescription();
	case 1:
	    return DateFormatConverter.convertDateToString(competition.getDate());
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
