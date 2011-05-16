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
	private int startNr;
	private String prgClass;
	private int yearOfBirth;
	@OneToMany
	private Map<DeviceType, Mark> marks;
	@Embedded
	private Association association;


	public Athlete(int squadID, int startNr, String prgClass, String firstName,
			String lastName, String address, int yearOfBirth,
			Association association) {
		super(firstName, lastName, address);
		this.squadId = squadID;
		this.startNr = startNr;
		this.prgClass = prgClass;
		this.yearOfBirth = yearOfBirth;
		this.association = association;
		this.marks = new HashMap<DeviceType, Mark>();
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
		updateObservers();
	}

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId(int squadId) {
		this.squadId = squadId;
		updateObservers();
	}

	public int getStartNr() {
		return startNr;
	}

	public void setStartNr(int startNr) {
		this.startNr = startNr;
		updateObservers();
	}

	public String getPrgClass() {
		return prgClass;
	}

	public void setPrgClass(String prgClass) {
		this.prgClass = prgClass;
		updateObservers();
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
		updateObservers();
	}

	public Map<DeviceType, Mark> getMarks() {
		return marks;
	}

	public void setMarks(Map<DeviceType, Mark> marks) {
		this.marks = marks;
		updateObservers();
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
		updateObservers();
	}

	@Override
	public String toString() {
//		return String.format("(%d, %d)", this.squadId, this.startNr);
	    return super.getFirstName() + " " + super.getLastName();
	}

	public void addMark(DeviceType dt, Mark mark) {
		marks.put(dt, mark);
		updateObservers();

	}

	public double getSumOfEndMarks() {
		double sumOfEndMarks = 0.0;
		for (DeviceType deviceType: DeviceType.values()) {
			sumOfEndMarks = sumOfEndMarks + marks.get(deviceType).getFinalMark();
		}
		return sumOfEndMarks;
	}
	private void updateObservers(){
		setChanged();
		notifyObservers();
	}
}
