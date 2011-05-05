package ch.hsr.gymtastic.application.controller.client;

import java.util.Observable;

import ch.hsr.gymtastic.domain.CompetitionInfo;

public class CompetitionInfoController extends Observable {
	private CompetitionInfo competitionInfo;

	public void setComeptitionInfo(Object arg) {
		this.competitionInfo = (CompetitionInfo) arg;
		updateObservers();
	}

	public void updateObservers() {
		setChanged();
		notifyObservers();
	}

	public CompetitionInfo getCompetitionInfo() {
		return competitionInfo;
	}

}
