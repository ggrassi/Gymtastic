package ch.hsr.gymtastic.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.RoundInfo;

public class RoundInfoController extends Observable {
	private RoundInfo roundInfo;

	public void setRoundInfo(Object arg) {
		this.roundInfo = (RoundInfo) arg;
		updateObservers();
	}

	public void updateObservers() {
		setChanged();
		notifyObservers();
	}
	
	public RoundInfo getRoundInfo()
	{
		return roundInfo;
	}

}
