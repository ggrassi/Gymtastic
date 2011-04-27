package ch.hsr.gymtastic.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.GymCupClientInfo;

public class GymCupInfoController extends Observable {
	private GymCupClientInfo gymCupInfo;

	public void setGymCupInfo(Object arg) {
		this.gymCupInfo = (GymCupClientInfo) arg;
		updateObservers();
	}
	
	public GymCupClientInfo getGymCupClientInfo()
	{
		return gymCupInfo;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}
	

}
