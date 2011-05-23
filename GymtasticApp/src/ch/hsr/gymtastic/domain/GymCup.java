package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class GymCup is the main Class and holds all Informations about the Cup.
 * The most important Infos are all competitions and all Squads with its
 * Athletes.
 * 
 */
@Entity
public class GymCup extends Observable {

	@Id
	private int id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Competition> competitions = new ArrayList<Competition>();
	@OneToMany(cascade = CascadeType.ALL)
	private Map<Integer, Squad> squads = new HashMap<Integer, Squad>();
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<Squad> squadsUnallocated = new ArrayList<Squad>();
	@OneToMany(cascade = CascadeType.ALL)
	private Set<String> programClasses = new HashSet<String>();
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private String sponsors;
	private String location;
	private String name;
	private String description;
	private String logoImagePath = "";

	/**
	 * Instantiates a new gym cup.
	 * 
	 * @param name
	 *            the name
	 * @param ort
	 *            the ort
	 */
	public GymCup(String name, String ort) {
		this.name = name;
		this.location = ort;

	}

	/**
	 * Instantiates a new gym cup.
	 */
	public GymCup() {
	}

	/**
	 * Gets the sponsors.
	 * 
	 * @return the sponsors
	 */
	public String getSponsors() {
		return sponsors;
	}

	/**
	 * Sets the sponsors.
	 * 
	 * @param sponsors
	 *            the new sponsors
	 */
	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
		updateObservers();
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(String location) {
		this.location = location;
		updateObservers();
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
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
		updateObservers();

	}

	/**
	 * Adds the squad unallocated.
	 * 
	 * @param s
	 *            the s
	 */
	public void addSquadUnallocated(Squad s) {
		squadsUnallocated.add(s);
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
	 * Gets the competitions.
	 * 
	 * @return the competitions
	 */
	public List<Competition> getCompetitions() {
		return competitions;
	}

	/**
	 * Sets the competitions.
	 * 
	 * @param competitions
	 *            the new competitions
	 */
	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
		updateObservers();
	}

	/**
	 * Gets the squads.
	 * 
	 * @return the squads
	 */
	public Map<Integer, Squad> getSquads() {
		return squads;
	}

	/**
	 * Gets the unallocated squads.
	 * 
	 * @return the unallocated squads
	 */
	public ArrayList<Squad> getUnallocatedSquads() {
		return squadsUnallocated;
	}

	/**
	 * Sets the squads.
	 * 
	 * @param squads
	 *            the squads
	 */
	public void setSquads(Map<Integer, Squad> squads) {
		this.squads = squads;
		updateObservers();
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
		updateObservers();

	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public GregorianCalendar getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
		updateObservers();

	}

	/**
	 * Adds the squad.
	 * 
	 * @param index
	 *            the index
	 * @param squad
	 *            the squad
	 */

	public void addSquad(int index, Squad squad) {
		squads.put(index, squad);
		updateObservers();

	}

	/**
	 * Gets the squad.
	 * 
	 * @param index
	 *            the index
	 * @return the squad
	 */
	public Squad getSquad(int index) {
		return squads.get(index);
	}

	/**
	 * Gets the logo image path.
	 * 
	 * @return the logo image path
	 */
	public String getLogoImagePath() {
		return logoImagePath;
	}

	/**
	 * Sets the logo image path.
	 * 
	 * @param logoImagePath
	 *            the new logo image path
	 */
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
		updateObservers();
	}

	/**
	 * Gets all athletes.
	 * 
	 * @return the all athletes
	 */
	public List<Athlete> getAllAthletes() {
		List<Athlete> athletes = new ArrayList<Athlete>();
		for (Squad squad : squads.values()) {
			athletes.addAll(squad.getAthlets());
		}
		return athletes;
	}

	/**
	 * Gets the program classes.
	 * 
	 * @return the program classes
	 */
	public Set<String> getProgramClasses() {
		return programClasses;
	}

	/**
	 * Sets the program classes.
	 * 
	 * @param programClasses
	 *            the new program classes
	 */
	public void setProgramClasses(Set<String> programClasses) {
		this.programClasses = programClasses;
	}

	/**
	 * Adds the program class.
	 * 
	 * @param programClass
	 *            the program class
	 */
	public void addProgramClass(String programClass) {
		programClasses.add(programClass);
		updateObservers();
	}

	/**
	 * Adds a competition.
	 * 
	 * @param competition
	 *            the competition
	 * @return the boolean
	 */
	public Boolean addCompetition(Competition competition) {
		if (competition != null) {
			Boolean b = competitions.add(competition);
			updateObservers();
			return b;
		} else {
			return false;
		}
	}

	/**
	 * Update squad.
	 * 
	 * @param squad
	 *            the squad
	 */
	public void updateSquad(Squad squad) {
		System.out.println("Updating Squad: " + squad.getSquadId());
		for (Squad s : squads.values()) {
			if (s.getSquadId() == squad.getSquadId()) {
				squads.put(squad.getSquadId(), squad);
				System.out.println("Squad overriden");
			}
		}
		updateObservers();
	}

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Athlete has changed and updates the observers.
	 */
	public void athleteChanged() {
		updateObservers();
	}

	/**
	 * Adds the athlete to squad.
	 * 
	 * @param squadNr
	 *            the squad nr
	 * @param athlete
	 *            the athlete
	 */
	public void addAthleteToSquad(int squadNr, Athlete athlete) {
		squads.get(squadNr).addAthlet(athlete);
	}

	/**
	 * Removes an athlete from his squad.
	 * 
	 * @param athlete
	 *            the athlete
	 */
	public void removeAthleteFromSquad(Athlete athlete) {
		squads.get(athlete.getSquadId()).removeAthlete(athlete);
		updateObservers();
	}

	/**
	 * Adds the squad to the competition.
	 * 
	 * @param squad
	 *            the squad
	 * @param competition
	 *            the competition
	 */
	public void addSquadToCompetition(Squad squad, Competition competition) {
		competition.addSquad(squads.get(squad.getSquadId()));
		squadsUnallocated.remove(squad);
	}

	/**
	 * Adds the squads to competition.
	 * 
	 * @param selectedSquads
	 *            the selected squads
	 * @param competition
	 *            the competition
	 */
	public void addSquadsToCompetition(List<Squad> selectedSquads,
			Competition competition) {
		for (Squad squad : selectedSquads) {
			competition.addSquad(squads.get(squad.getSquadId()));
		}
//		squadsUnallocated.removeAll(selectedSquads);

	}

}
