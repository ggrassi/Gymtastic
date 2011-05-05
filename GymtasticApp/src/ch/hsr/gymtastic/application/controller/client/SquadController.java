package ch.hsr.gymtastic.application.controller.client;

import java.util.Observable;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;

public class SquadController extends Observable {
	private Squad squad;
	private int numberOfAthletes = 0;
	private int roundNr;
	private int index = -1;

	public SquadController() {
	}

	public boolean hasNextAthlete() {
		if (index < numberOfAthletes - 1)
			return true;
		return false;
	}

	public Athlete getNextAthlete() {
		return squad.getAthlete(++index);
	}

	public boolean hasPreviousAthlete() {
		if (index > 0)
			return true;
		return false;

	}

	public Athlete getPreviousAthlete() {
		return squad.getAthlete(--index);
	}

	public int getNumberOfAthletes() {
		return numberOfAthletes;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
		numberOfAthletes = this.squad.getSquadSize();
		reset();
		updateObservers();
	}

	public Squad getSquad() {
		return squad;
	}

	public void reset() {
		index = -1;
	}

	public void setRoundInfo(Object arg) {
		RoundInfo roundInfo = (RoundInfo) arg;
		setSquad(roundInfo.getSquad());
		setRoundNr(roundInfo.getRoundNr());
		
	}

	public void setRoundNr(int roundNr) {
		this.roundNr = roundNr;
		updateObservers();
	}

	public int getRoundNr() {
		return roundNr;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}


}
