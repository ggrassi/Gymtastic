package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Athlete holds all Informations of an Athlete. Important are the ID,
 * ProgrammClass and all his Marks.
 */
@Entity
public class Athlete extends Observable implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -18961899248373223L;
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int squadId;
	private int startNr;
	private int yearOfBirth;
	@OneToMany
	private Map<DeviceType, Mark> marks = new HashMap<DeviceType, Mark>();
	@Embedded
	private String association;
	private String prgClass;
	private String firstName;
	private String lastName;
	private String address;

	/**
	 * Instantiates a new athlete.
	 * 
	 * @param squadID
	 *            the squad id
	 * @param startNr
	 *            the start nr
	 * @param prgClass
	 *            the prg class
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param address
	 *            the address
	 * @param yearOfBirth
	 *            the year of birth
	 * @param association
	 *            the association
	 */
	public Athlete(int squadID, int startNr, String prgClass, String firstName,
			String lastName, String address, int yearOfBirth, String association) {
		this.squadId = squadID;
		this.startNr = startNr;
		this.prgClass = prgClass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.yearOfBirth = yearOfBirth;
		this.association = association;
	}

	/**
	 * Instantiates a new athlete.
	 * 
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param address
	 *            the address
	 */
	public Athlete(String firstName, String lastName, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	/**
	 * Instantiates a new athlete.
	 */
	public Athlete() {
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
		this.startNr= startNr;
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

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
		updateObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	/**
	 * Adds a mark to a map containing marks depending on its DeviceType as Key.
	 * 
	 * @param dt
	 *            the dt
	 * @param mark
	 *            the mark
	 */
	public void addMark(DeviceType dt, Mark mark) {
		marks.put(dt, mark);
		updateObservers();
	}

	/**
	 * Gets the sum of all end marks.
	 * 
	 * @return the sum of end marks
	 */
	public double getSumOfEndMarks() {
		double sumOfEndMarks = 0.0;
		for (DeviceType deviceType : DeviceType.values()) {
			sumOfEndMarks = sumOfEndMarks
					+ marks.get(deviceType).getFinalMark();
		}
		return sumOfEndMarks;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	public Mark getMark(DeviceType deviceType) {
		return marks.get(deviceType);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return startNr;
	}

	public void setId(int id) {
		this.id = id;
	}
}
