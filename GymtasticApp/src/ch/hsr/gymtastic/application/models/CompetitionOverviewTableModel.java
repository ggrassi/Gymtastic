package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Competition;

public class CompetitionOverviewTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private String[] columns = { "Beschreibung", "Datum" };
    private final CompetitionModel competitionModel;

    public CompetitionOverviewTableModel(CompetitionModel competitionModel) {
	this.competitionModel = competitionModel;
	this.competitionModel.addObserver(this);
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
	if (competitionModel.getGymCup().getCompetitions() != null) {
	    return competitionModel.getGymCup().getCompetitions().size();
	} else {
	    return 0;
	}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Competition competition = competitionModel.getCompetition(rowIndex);
	switch (columnIndex) {
	case 0:
	    return competition.getDescription();
	case 1:
	    return competition.getDate();
	}
	return "";
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	fireTableDataChanged();
    }

}
