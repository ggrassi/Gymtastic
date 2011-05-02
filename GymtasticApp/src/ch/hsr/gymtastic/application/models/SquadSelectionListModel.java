package ch.hsr.gymtastic.application.models;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import ch.hsr.gymtastic.domain.Squad;

public class SquadSelectionListModel extends AbstractListModel implements Observer {

    private CompetitionModel competitionModel;
    private Map<Integer, Squad> values;

    public SquadSelectionListModel(CompetitionModel competitionModel) {
	this.competitionModel = competitionModel;
	this.competitionModel.addObserver(this);
	values = this.competitionModel.getGymCup().getSquads();
    }

    public int getSize() {
	return values.size();
    }

    public Object getElementAt(int index) {
	return competitionModel.getGymCup().getSquads().get(index);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
