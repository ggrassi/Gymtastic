package ch.hsr.gymtastic.client.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.GymCupClientInfo;

/**
 * The Class GymCupInfoController. Holds the GymCup Information which gets
 * exchanged between Client and Server.
 */
public class GymCupInfoController extends Observable {

    /** The gym cup info. */
    private GymCupClientInfo gymCupInfo;

    /**
     * Sets the gym cup info.
     * 
     * @param arg
     *            the new gym cup info
     */
    public void setGymCupInfo(Object arg) {
	this.gymCupInfo = (GymCupClientInfo) arg;
	updateObservers();
    }

    /**
     * Gets the gym cup client info.
     * 
     * @return the gym cup client info
     */
    public GymCupClientInfo getGymCupClientInfo() {
	return gymCupInfo;
    }

    /**
     * Update observers.
     */
    private void updateObservers() {
	setChanged();
	notifyObservers();
    }

}
