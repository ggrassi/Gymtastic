package ch.hsr.gymtastic.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Athlet extends Person {

    /**
     * 
     */
    private static final long serialVersionUID = -18961899248373223L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int squadId;
    private int athletId;
    private String prgClass;
    private int yearOfBirth;
    @OneToMany
    private Map<DeviceType, Mark> marks;
    @Embedded
    private Association association;

    public Athlet(int squadID, int athletID, String prgClass, String vorname, String name, String adresse,
	    int yearOfBirth, Association association) {
	super(name, vorname, adresse);
	this.squadId = squadID;
	this.athletId = athletID;
	this.prgClass = prgClass;
	this.yearOfBirth = yearOfBirth;
	this.association = association;
	marks = new HashMap<DeviceType, Mark>();
	marks.put(DeviceType.FLOOR_EXCERCISE, new Mark(4, 3, 4, 3, 4, 3));
    }

    public Athlet() {
	super();
    }

    public Athlet(String vorname, String nachname, String adresse) {
	super(vorname, nachname, adresse);
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public int getSquadId() {
	return squadId;
    }

    public void setSquadId(int squadId) {
	this.squadId = squadId;
    }

    public int getAthletId() {
	return athletId;
    }

    public void setAthletId(int athletId) {
	this.athletId = athletId;
    }

    public String getPrgClass() {
	return prgClass;
    }

    public void setPrgClass(String prgClass) {
	this.prgClass = prgClass;
    }

    public int getYearOfBirth() {
	return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
	this.yearOfBirth = yearOfBirth;
    }

    public Map<DeviceType, Mark> getMarks() {
	return marks;
    }

    public void setMarks(Map<DeviceType, Mark> marks) {
	this.marks = marks;
    }

    public Association getAssociation() {
	return association;
    }

    public void setAssociation(Association association) {
	this.association = association;
    }

    @Override
    public String toString() {
	return String.format("(%d, %d)", this.squadId, this.athletId);
    }

    public void addMark(DeviceType dt, Mark mark) {
	marks.put(dt, mark);

    }
}
