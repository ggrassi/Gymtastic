package ch.hsr.gymtastic.application.controller.server;

import java.util.Observable;

import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.domain.GymCup;

public class ModelController extends Observable {
    private CupManagementModel cupManagementModel;
    private CompetitionModel competitionModel;
    private GymCup gymCup;

    public ModelController(CupManagementModel cupManagementModel, CompetitionModel competitionModel) {
	this.cupManagementModel = cupManagementModel;
	this.competitionModel = competitionModel;
	this.addObserver(cupManagementModel);
	this.addObserver(competitionModel);
    }

    public void setGymCup(GymCup gymCup) {
	this.gymCup = gymCup;
	updateObservers();
    }

    public GymCup getGymCup() {
	return gymCup;
    }

    public void setModelControllerToModels() {
	cupManagementModel.setModelController(this);
	competitionModel.setModelController(this);
    }

    private void updateObservers() {
	setChanged();
	notifyObservers();
    }
}
