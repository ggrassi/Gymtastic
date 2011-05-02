package ch.hsr.gymtastic.application.controller.server;

import java.util.Observable;

import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.application.models.RankingModel;
import ch.hsr.gymtastic.domain.GymCup;

public class ModelController extends Observable {
    private CupManagementModel cupManagementModel;
    private CompetitionModel competitionModel;
    private GymCup gymCup;
	private RankingModel rankingModel;

    public ModelController(CupManagementModel cupManagementModel, CompetitionModel competitionModel, RankingModel rankingModel) {
	this.cupManagementModel = cupManagementModel;
	this.competitionModel = competitionModel;
	this.rankingModel = rankingModel;
	this.addObserver(cupManagementModel);
	this.addObserver(competitionModel);
	this.addObserver(rankingModel);
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
	rankingModel.setModelController(this);
    }

    private void updateObservers() {
	setChanged();
	notifyObservers();
    }
}
