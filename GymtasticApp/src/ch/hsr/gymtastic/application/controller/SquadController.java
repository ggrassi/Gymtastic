package ch.hsr.gymtastic.application.controller;

import java.util.Observable;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class SquadController extends Observable{
	private Squad squad;
	private int numberOfAthletes = 0;
	
	public SquadController() {
	}

	public Athlete getAthlete(int index){
		return this.squad.getAthlete(numberOfAthletes % index);
	}
	
	public int getNumberOfAthletes(){
		return this.numberOfAthletes;
	}
	
	public void setSquad(Object squad)
	{
		this.squad = (Squad) squad;
		updateObservers();
	}
	

	public Squad getSquad()
	{
		return this.squad;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

}
