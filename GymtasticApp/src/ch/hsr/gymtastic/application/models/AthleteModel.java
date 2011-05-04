package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.application.controller.server.ModelController;
import ch.hsr.gymtastic.domain.GymCup;

public class AthleteModel extends Observable implements Observer {

	private GymCup gymCup;
	private ModelController modelController;


	public void setGymCup(GymCup gymCup) {
		modelController.setGymCup(gymCup);

	}

	public GymCup getGymCup() {
		return gymCup;
	}

	// private void setControllerUpdates(GymCup gymCup) {
	// this.gymCup = gymCup;
	// changedNotifyObservers();
	// }

	private void setModelControllerUpdates() {
		gymCup = modelController.getGymCup();
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
		setGymCup(modelController.getGymCup());
	}

	public ModelController getModelController() {
		return modelController;
	}
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		setModelControllerUpdates();
		updateObservers();
	}

}
