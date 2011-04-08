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
public class Athlete extends Person {

    /**
     * 
     */
    private static final long serialVersionUID = -18961899248373223L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int squadId;
    private int athleteId;
    private String prgClass;
    private int yearOfBirth;
    @OneToMany
    private Map<DeviceType, Mark> marks;
    @Embedded
    private Association association;

    public Athlete(int squadID, int athleteID, String prgClass, String firstName, String lastName, String address,
	    int yearOfBirth, Association association) {
	super(firstName, lastName, address);
	this.squadId = squadID;
	this.athleteId = athleteID;
	this.prgClass = prgClass;
	this.yearOfBirth = yearOfBirth;
	this.association = association;
	marks = new HashMap<DeviceType, Mark>();
	marks.put(DeviceType.FLOOR_EXCERCISE, new Mark(4, 3, 4, 3, 4, 3));
    }

    public Athlete() {
	super();
    }

    public Athlete(String firstName, String lastName, String address) {
	super(firstName, lastName, address);
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

    public int getAthleteId() {
	return athleteId;
    }

    public void setAthleteId(int athletId) {
	this.athleteId = athletId;
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
	return String.format("(%d, %d)", this.squadId, this.athleteId);
    }

    public void addMark(DeviceType dt, Mark mark) {
	marks.put(dt, mark);

    }
}
