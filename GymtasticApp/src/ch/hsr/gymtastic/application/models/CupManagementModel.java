package ch.hsr.gymtastic.application.models;

import java.util.Observable;

import ch.hsr.gymtastic.domain.GymCup;

public class CupManagementModel extends Observable {

    private GymCup gymCup;

    public void setGymcup(GymCup gymCup) {
	this.gymCup = gymCup;
	changedNotifyObservers();
    }

    private void changedNotifyObservers() {
	setChanged();
	notifyObservers();
    }

}
