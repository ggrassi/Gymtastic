package ch.hsr.gymtastic.client.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.CompetitionInfo;


/**
 * The Class CompetitionInfoController.
 * Holds the CompetitionuInformation which gets 
 * exchanged between Server and Client.
 */
public class CompetitionInfoController extends Observable {
	/** The competition info. */
	private CompetitionInfo competitionInfo;

	/**
	 * Sets the comeptition info.
	 *
	 * @param arg the new comeptition info
	 */
	public void setComeptitionInfo(Object arg) {
		this.competitionInfo = (CompetitionInfo) arg;
		updateObservers();
	}

	/**
	 * Update observers.
	 */
	public void updateObservers() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the competition info.
	 *
	 * @return the competition info
	 */
	public CompetitionInfo getCompetitionInfo() {
		return competitionInfo;
	}

}
