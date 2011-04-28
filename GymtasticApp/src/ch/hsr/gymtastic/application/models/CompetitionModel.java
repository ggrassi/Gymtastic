package ch.hsr.gymtastic.application.models;

import java.util.List;
import java.util.Observable;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class CompetitionModel extends Observable {
	private GymCup gymCup;

	public void setExistingGymcup() {
		DBConnection db = new DBConnection();
		System.out.println(DBConnection.getPath());
		TypedQuery<GymCup> query = db.getEm().createQuery(
				"SELECT p FROM GymCup p", GymCup.class);
		List<GymCup> result = query.getResultList();
		if (result.size() == 1) {
			int first = 0;
			this.gymCup = result.get(first);
		}
		
		db.commit();
		db.closeConnection();
		changedNotifyObservers();
	}

	public void setGymcup(GymCup gymCup) {
		this.gymCup = gymCup;
		changedNotifyObservers();
	}

	public GymCup getGymCup() {
		return gymCup;
	}

	private void changedNotifyObservers() {
		setChanged();
		notifyObservers();
	}

}
