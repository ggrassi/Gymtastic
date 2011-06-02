package ch.hsr.gymtastic.client.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.CompetitionInfo;


/**
 * The Class CompetitionInfoController.
 * Holds the CompetitionuInformation which gets 
 * exchanged between Server and Client.
 */
public class CompetitionInfoController extends Observable {

	private CompetitionInfo competitionInfo;

	public void setComeptitionInfo(Object arg) {
		this.competitionInfo = (CompetitionInfo) arg;
		updateObservers();
	}

	public CompetitionInfo getCompetitionInfo() {
		return competitionInfo;
	}

	public void updateObservers() {
		setChanged();
		notifyObservers();
	}
}
