package ch.hsr.gymtastic.application.models;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.application.controller.server.ModelController;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class CompetitionModel extends Observable implements Observer {
    private GymCup gymCup;
    private ModelController modelController;
    private Competition selectedCompetition;
    private Competition actualCompetition;

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
	updateObservers();
    }

    public void setGymCup(GymCup gymCup) {
//	this.gymCup = gymCup;
	modelController.setGymCup(gymCup);
	updateObservers();
    }

    public GymCup getGymCup() {
	return gymCup;
    }

    public boolean addCompetitionToGymCup(Competition competition) {
	if (gymCup.addCompetition(competition)) {
	    setGymCup(gymCup);
	    return true;
	} else {
	    return false;
	}

    }

    public Competition getCompetition(int index) {
	return gymCup.getCompetitions().get(index);
    }
    
    public void setModelController(ModelController modelController) {
	this.modelController = modelController;
    }

    private void setModelControllerUpdates() {
	gymCup = modelController.getGymCup();
    }
    
    private void updateObservers() {
	setChanged();
	notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
	setModelControllerUpdates();
    }

    public void addSquadToCompetition(int modelRow) {
//	welche Competition?
	
    }
    
    public void setSelectedCompetition(int index){
	selectedCompetition = gymCup.getCompetitions().get(index);
	updateObservers();
    }

    public void setActualCompetition(Competition competition) {
	actualCompetition = competition;
	updateObservers();
    }
    
    public Competition getActualCompetition(){
	return actualCompetition;
    }

}
