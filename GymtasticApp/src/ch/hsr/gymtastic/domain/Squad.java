package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

// TODO: Auto-generated Javadoc
/**
 * The Class Squad holds a List of Athletes
 */
@Entity
public class Squad extends Observable implements Serializable {

    private static final long serialVersionUID = 3240358878521229793L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

    /**
     * Adds an athlet to the Squad
     * 
     * @param athlet
     *            the athlet
     */
    public void addAthlet(Athlete athlet) {
	athlets.add(athlet);
	updateObservers();
    }

    /**
     * Gets the squad size.
     * 
     * @return the squad size
     */
    public int getSquadSize() {
	return athlets.size();
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public int getId() {
	return squadId;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the new id
     */
    public void setId(int id) {
	this.id = id;
	updateObservers();
    }

    /**
     * Gets the squad id.
     * 
     * @return the squad id
     */
    public int getSquadId() {
	return squadId;
    }

    /**
     * Gets the athlets.
     * 
     * @return the athlets
     */
    public List<Athlete> getAthlets() {
	return athlets;
    }

    /**
     * Sets the athlets.
     * 
     * @param athlets
     *            the new athlets
     */
    public void setAthlets(List<Athlete> athlets) {
	this.athlets = athlets;
	updateObservers();
    }

    /**
     * Gets the athlete.
     * 
     * @param index
     *            the index
     * @return the athlete
     */
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

    /**
     * Update observers.
     */
    private void updateObservers() {
	setChanged();
	notifyObservers();
    }

    /**
     * Removes the athlete.
     * 
     * @param athlete
     *            the athlete
     */
    public void removeAthlete(Athlete athlete) {
	athlets.remove(athlete);
    }
}
