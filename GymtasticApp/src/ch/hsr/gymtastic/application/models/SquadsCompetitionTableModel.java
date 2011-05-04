package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

public class SquadsCompetitionTableModel extends AbstractTableModel implements Observer {

    private String[] columns = { "Riege" };
    private final CompetitionModel competitionModel;

    public SquadsCompetitionTableModel(CompetitionModel competitionModel) {
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
	if (competitionModel.getActualCompetition() != null) {
	    return competitionModel.getActualCompetition().getSquads().size();
	} else {
	    return 0;
	}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	case 0:
	    return competitionModel.getActualCompetition().getSquads().get(rowIndex);
	}
	return "";
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	
	fireTableDataChanged();
    }

}