package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.application.controller.ClientAllocation;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;
@Entity
public class GymCup {
	@Id
    private int id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Competition> competitions = new ArrayList<Competition>();
	@OneToMany(cascade = CascadeType.ALL)
	private Map<Integer, Squad> squads = new HashMap<Integer, Squad>();
	private ClientAllocation clientAlloc;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private String sponsers;
	private String ort;
	private String name;

	public GymCup(String name, String ort) {
		this.name = name;
		this.ort = ort;
		importGymCupToDB();
	}

	private void importGymCupToDB() {
		DBConnection db = new DBConnection();
		db.persist(this);
		db.commit();
		db.closeConnection();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public Map<Integer, Squad> getSquads() {
		return squads;
	}

	public void setSquads(Map<Integer, Squad> squads) {
		this.squads = squads;
	}

	public ClientAllocation getClientAlloc() {
		return clientAlloc;
	}

	public void setClientAlloc(ClientAllocation clientAlloc) {
		this.clientAlloc = clientAlloc;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public String getSponsers() {
		return sponsers;
	}

	public void setSponsers(String sponsers) {
		this.sponsers = sponsers;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSquad(int index, Squad squad) {
		squads.put(index, squad);
	}

	public Squad getSquad(int index) {
		return squads.get(index);
	}


}
