package ch.hsr.gymtastic.application.controller;

import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.Gymcup;
import ch.hsr.gymtastic.presentation.ServerFrame;

public class GymCupController extends Observable implements Observer{
	
	private Gymcup gymCup = null;

	public GymCupController() {
		
	}	

	public void updateManual() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	public void setGymcup(Gymcup gymcup) {
		this.gymCup = gymcup;
		updateManual();
		
	}

	
	
}
