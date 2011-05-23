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

	public GymCup(String name, String ort) {
		this.name = name;
		this.location = ort;

	}

	public GymCup() {
	}

	public String getSponsors() {
		return sponsors;
	}

	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
		updateObservers();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		updateObservers();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		updateObservers();

	}

	public void addSquadUnallocated(Squad s) {
		squadsUnallocated.add(s);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		updateObservers();
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
		updateObservers();
	}

	public Map<Integer, Squad> getSquads() {
		return squads;
	}

	public ArrayList<Squad> getUnallocatedSquads() {
		return squadsUnallocated;
	}

	public void setSquads(Map<Integer, Squad> squads) {
		this.squads = squads;
		updateObservers();
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
		updateObservers();

	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		updateObservers();

	}

	public void addSquad(int index, Squad squad) {
		squads.put(index, squad);
		updateObservers();

	}

	public Squad getSquad(int index) {
		return squads.get(index);
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}

	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
		updateObservers();
	}

	public List<Athlete> getAllAthletes() {
		List<Athlete> athletes = new ArrayList<Athlete>();
		for (Squad squad : squads.values()) {
			athletes.addAll(squad.getAthlets());
		}
		return athletes;
	}

	public Set<String> getProgramClasses() {
		return programClasses;
	}

	public void setProgramClasses(Set<String> programClasses) {
		this.programClasses = programClasses;
	}

	public void addProgramClass(String programClass) {
		programClasses.add(programClass);
		updateObservers();
	}

	public Boolean addCompetition(Competition competition) {
		if (competition != null) {
			Boolean b = competitions.add(competition);
			updateObservers();
			return b;
		} else {
			return false;
		}
	}

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

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	public void athleteChanged() {
		updateObservers();
	}

	public void addAthleteToSquad(int squadNr, Athlete athlete) {
		squads.get(squadNr).addAthlet(athlete);
	}

	public void removeAthleteFromSquad(Athlete athlete) {
		squads.get(athlete.getSquadId()).removeAthlete(athlete);
		updateObservers();
	}

	public void addSquadToCompetition(Squad squad, Competition competition) {
		competition.addSquad(squads.get(squad.getSquadId()));
		squadsUnallocated.remove(squad);
	}

	public void addSquadsToCompetition(List<Squad> selectedSquads,
			Competition competition) {
		for (Squad squad : selectedSquads) {
			competition.addSquad(squads.get(squad.getSquadId()));
		}
		squadsUnallocated.removeAll(selectedSquads);

	}

}