package ch.hsr.gymtastic.application.controller;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class PresentationClientController {
	Squad squad;
	int numberOfAthletes = 0;
	
	public PresentationClientController(NetworkClientController networkController){
		this.squad = networkController.getSquad();
		this.numberOfAthletes = this.squad.getAthlets().size();
	}
	
	public Athlete getAthlete(int index){
		return this.squad.getAthlete(numberOfAthletes % index);
	}
	
	public int getNumberOfAthletes(){
		return this.numberOfAthletes;
	}
	
	

}
