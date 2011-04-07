package ch.hsr.gymtastic.domain;

import java.util.*;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.technicalServices.db.DBConnection;

public class Gymcup {

	private List<Competition> competitions = new ArrayList<Competition>();
	private Map<Integer, Squad> squads;
	private ClientAllocation clientAlloc;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private String sponsers;
	private String ort;
	private String name;

	public Gymcup(String name, String ort) {
		this.name = name;
		this.ort = ort;
		squads = new HashMap<Integer, Squad>();
	}

	public void importAllSquads() {
		DBConnection db = new DBConnection();
		TypedQuery<Squad> query = db.getEm().createQuery(
				"SELECT p FROM Squad p", Squad.class);
		List<Squad> results = query.getResultList();
		for (Squad p : results) {
			squads.put(p.getSquadId(), p);
		}
		db.commit();
		db.closeConnection();

	}

	public void addCompetition(Competition competition) {
		competitions.add(competition);
	}

	public void removeCompetition(Competition competition) {
		competitions.remove(competition);
	}

	public void addSquads(Map<Integer, Squad> squads) {
		this.squads = squads;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public Squad getSquad(int index) {
		return squads.get(index);
	}

	public Map<Integer, Squad> getSquads() {
		return squads;
	}

}
