package ch.hsr.gymtastic.client.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.GymCupClientInfo;

/**
 * The Class GymCupInfoController. Holds the GymCup Information which gets
 * exchanged between Client and Server.
 */
public class GymCupInfoController extends Observable {

	private GymCupClientInfo gymCupInfo;

	public void setGymCupInfo(Object arg) {
		this.gymCupInfo = (GymCupClientInfo) arg;
		updateObservers();
	}

	public GymCupClientInfo getGymCupClientInfo() {
		return gymCupInfo;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

}
