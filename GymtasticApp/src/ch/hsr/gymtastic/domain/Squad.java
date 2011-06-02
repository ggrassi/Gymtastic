package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

// TODO: Auto-generated Javadoc
/**
 * The Class Squad holds a List of Athletes
 */
@Entity
public class Squad extends Observable implements Serializable {

	private static final long serialVersionUID = 3240358878521229793L;
	private int squadId;
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("athleteId ASC")
	private List<Athlete> athlets;

	/**
	 * Instantiates a new squad.
	 */
	public Squad() {
		athlets = new LinkedList<Athlete>();
	}

	/**
	 * Instantiates a new squad.
	 * 
	 * @param squadId
	 *            the squad id
	 */
	public Squad(int squadId) {
		this.squadId = squadId;
		athlets = new LinkedList<Athlete>();
	}

	public void addAthlet(Athlete athlet) {
		athlets.add(athlet);
		updateObservers();
	}

	public int getSquadSize() {
		return athlets.size();
	}

	public int getSquadId() {
		return squadId;
	}

	public List<Athlete> getAthlets() {
		return athlets;
	}

	public void setAthlets(List<Athlete> athlets) {
		this.athlets = athlets;
		updateObservers();
	}

	public Athlete getAthlete(int index) {
		return athlets.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "" + squadId;
	}

	public void removeAthlete(Athlete athlete) {
		athlets.remove(athlete);
	}
	
	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}
	
}
