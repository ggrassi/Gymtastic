package ch.hsr.gymtastic.client.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;

/**
 * The Class SquadController manages a squad.
 */
public class SquadController extends Observable {
	
	private Squad squad;
	private int numberOfAthletes = 0;
	private int roundNr;
	private int index = -1;

	/**
	 * Instantiates a new squad controller.
	 */
	public SquadController() {
	}

	/**
	 * Checks for next athlete.
	 *
	 * @return true, if successful
	 */
	public boolean hasNextAthlete() {
		if (index < numberOfAthletes - 1)
			return true;
		return false;
	}

	/**
	 * Gets the next athlete.
	 *
	 * @return the next athlete
	 */
	public Athlete getNextAthlete() {
		return squad.getAthlete(++index);
	}

	/**
	 * Checks for previous athlete.
	 *
	 * @return true, if successful
	 */
	public boolean hasPreviousAthlete() {
		if (index > 0)
			return true;
		return false;

	}

	/**
	 * Gets the previous athlete.
	 *
	 * @return the previous athlete
	 */
	public Athlete getPreviousAthlete() {
		return squad.getAthlete(--index);
	}

	/**
	 * Gets the number of athletes.
	 *
	 * @return the number of athletes
	 */
	public int getNumberOfAthletes() {
		return numberOfAthletes;
	}

	/**
	 * Sets the squad.
	 *
	 * @param squad the new squad
	 */
	public void setSquad(Squad squad) {
		this.squad = squad;
		numberOfAthletes = this.squad.getSquadSize();
		reset();
		updateObservers();
	}

	/**
	 * Gets the squad.
	 *
	 * @return the squad
	 */
	public Squad getSquad() {
		return squad;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		index = -1;
	}

	/**
	 * Sets the round info.
	 *
	 * @param arg the new round info
	 */
	public void setRoundInfo(Object arg) {
		RoundInfo roundInfo = (RoundInfo) arg;
		setSquad(roundInfo.getSquad());
		setRoundNr(roundInfo.getRoundNr());
		
	}

	/**
	 * Sets the round nr.
	 *
	 * @param roundNr the new round nr
	 */
	public void setRoundNr(int roundNr) {
		this.roundNr = roundNr;
		updateObservers();
	}

	/**
	 * Gets the round nr.
	 *
	 * @return the round nr
	 */
	public int getRoundNr() {
		return roundNr;
	}

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}


}
