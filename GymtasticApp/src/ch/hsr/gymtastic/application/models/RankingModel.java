package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ch.hsr.gymtastic.application.controller.server.ModelController;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;

public class RankingModel extends Observable implements Observer {
	private GymCup gymCup;
	private ModelController modelController;



	public GymCup getGymCup() {
		return gymCup;
	}


	public Competition getCompetition(int index) {
		return gymCup.getCompetitions().get(index);
	}
	
	public Vector<Competition> getCompetitions()
	{
		Vector<Competition> vector = new Vector<Competition>();
		for (Competition competition : gymCup.getCompetitions()) {
			vector.add(competition);
		}
		return vector;
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
		updateObservers();
	}

	private void setModelControllerUpdates() {
		gymCup = modelController.getGymCup();
		updateObservers();
		
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		setModelControllerUpdates();
	}

}
