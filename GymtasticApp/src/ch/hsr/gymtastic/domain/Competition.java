package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Competition holds all informations about a Competition. The most important members are the Squads and the ProgramClass
 */
@Entity
public class Competition extends Observable {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Squad> squads = new ArrayList<Squad>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String startTime;
	private String endTime;
	private GregorianCalendar date;
	private String programClass;

	/**
	 * Instantiates a new competition.
	 *
	 * @param description the description
	 * @param date the date
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param programClass the program class
	 */
	public Competition(String description, GregorianCalendar date,
			String startTime, String endTime, String programClass) {
		this.description = description;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.programClass = programClass;
	}

	/**
	 * Gets the squads.
	 *
	 * @return the squads
	 */
	public List<Squad> getSquads() {
		return squads;
	}

	/**
	 * Sets the squads.
	 *
	 * @param squads the new squads
	 */
	public void setSquads(List<Squad> squads) {
		this.squads = squads;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * Gets the program class.
	 *
	 * @return the program class
	 */
	public String getProgramClass() {
		return programClass;
	}

	/**
	 * Sets the program class.
	 *
	 * @param programClass the new program class
	 */
	public void setProgramClass(String programClass) {
		this.programClass = programClass;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Adds the squad.
	 *
	 * @param squad the squad
	 */
	public void addSquad(Squad squad) {
		squads.add(squad);
	}

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the all athletes.
	 *
	 * @return the all athletes
	 */
	public List<Athlete> getAllAthletes() {
		List<Athlete> athletes = new ArrayList<Athlete>();
		for (Squad s : squads) {
			athletes.addAll(s.getAthlets());
		}
		return athletes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}

	/**
	 * Squads added.
	 */
	public void squadsAdded() {
		updateObservers();
	}

	/**
	 * Removes the squad.
	 *
	 * @param squad the squad
	 * @return true, if successful
	 */
	public boolean removeSquad(Squad squad) {
		return squads.remove(squad);
	}

}
