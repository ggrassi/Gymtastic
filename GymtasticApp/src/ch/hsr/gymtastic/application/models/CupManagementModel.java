package ch.hsr.gymtastic.application.models;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.application.controller.server.ModelController;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class CupManagementModel extends Observable implements Observer {

    private GymCup gymCup;
    private ModelController modelController;

    public void setExistingGymcup() {
	DBConnection db = new DBConnection();
	System.out.println(DBConnection.getPath());
	TypedQuery<GymCup> query = db.getEm().createQuery("SELECT p FROM GymCup p", GymCup.class);
	List<GymCup> result = query.getResultList();
	if (result.size() == 1) {
	    int first = 0;
	    this.gymCup = result.get(first);
	}

	db.commit();
	db.closeConnection();
	modelController.setGymCup(gymCup);
	updateObservers();
    }

    public void setGymCup(GymCup gymCup) {
	// this.gymCup = gymCup;
	modelController.setGymCup(gymCup);
	updateObservers();
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
