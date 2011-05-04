
package ch.hsr.gymtastic.application.controller.server;

import java.util.Observable;

import ch.hsr.gymtastic.application.models.AthleteModel;
import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.application.models.RankingModel;
import ch.hsr.gymtastic.domain.GymCup;

public class ModelController extends Observable {
	private CupManagementModel cupManagementModel;
	private CompetitionModel competitionModel;
	private AthleteModel athleteModel;
	private GymCup gymCup;
	private RankingModel rankingModel;

	public ModelController(CupManagementModel cupManagementModel,
			CompetitionModel competitionModel, RankingModel rankingModel, AthleteModel athleteModel) {
		this.cupManagementModel = cupManagementModel;
		this.competitionModel = competitionModel;
		this.rankingModel = rankingModel;
		this.athleteModel = athleteModel;
		this.addObserver(cupManagementModel);
		this.addObserver(competitionModel);
		this.addObserver(rankingModel);
		this.addObserver(athleteModel);
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
		athleteModel.setModelController(this);
	}

	public void updateObservers() {
		setChanged();
		notifyObservers();
	}
}
