package ch.hsr.gymtastic.domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.application.controller.server.ClientAllocator;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

@Entity
public class GymCup {
    @Id
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Competition> competitions = new ArrayList<Competition>();
    @OneToMany(cascade = CascadeType.ALL)
    private Map<Integer, Squad> squads = new HashMap<Integer, Squad>();
    private ClientAllocator clientAlloc;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String sponsors;
    private String location;
    private String name;
    private String description;
    private BufferedImage logoImage;

    public String getSponsors() {
	return sponsors;
    }

    public void setSponsors(String sponsors) {
	this.sponsors = sponsors;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public GymCup(String name, String ort) {
	this.name = name;
	this.location = ort;
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
	TypedQuery<Squad> query = db.getEm().createQuery("SELECT p FROM Squad p", Squad.class);
	List<Squad> results = query.getResultList();
	GymCup tmpCup;
	Squad tmpSquad;
	for (Squad p : results) {
	    tmpCup = db.getEm().find(GymCup.class, this.getId());
	    tmpSquad = db.getEm().find(Squad.class, p.getId());
	    tmpCup.addSquad(tmpSquad.getSquadId(), tmpSquad);
	    db.persist(tmpCup);
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

    public ClientAllocator getClientAlloc() {
	return clientAlloc;
    }

    public void setClientAlloc(ClientAllocator clientAlloc) {
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

    public void setImage(BufferedImage image) {

    }

    public void setLogoImage(BufferedImage logoImage) {
	this.logoImage = logoImage;
    }

    public BufferedImage getLogoImage() {
	return logoImage;
    }

    public void setStartDateStr(String strStartDate) {
	DBConnection db = new DBConnection();
	GymCup tmpCup = db.getEm().find(GymCup.class, this.getId());
	extractDateInto(strStartDate, tmpCup);
	db.commit();
	db.closeConnection();
    }

    private void extractDateInto(String strDate, GymCup tmpCup) {
	// if (!strDate.equals(null)) {
	if (!strDate.equals("")) {
	    String[] tmp = strDate.split("\\.");

	    tmpCup.setStartDate(new GregorianCalendar(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[1]), Integer
		    .parseInt(tmp[0])));
	} else {
	    tmpCup.setStartDate(new GregorianCalendar());
	}
    }

    public void setEndDateStr(String strEndDate) {
	DBConnection db = new DBConnection();
	GymCup tmpCup = db.getEm().find(GymCup.class, this.getId());
	extractDateInto(strEndDate, tmpCup);
	db.commit();
	db.closeConnection();
    }

}
