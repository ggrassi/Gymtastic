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

	
	private int squadId;
	private int startNr;
	private String prgClass;
	private int yearOfBirth;
	@OneToMany
	private Map<DeviceType, Mark> marks = new HashMap<DeviceType, Mark>();
	@Embedded
	private String association;
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
	 */
	public Athlete() {
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
	 * Gets the squad id.
	 * 
	 * @return the squad id
	 */
	public int getSquadId() {
		return squadId;
	}

	/**
	 * Sets the squad id.
	 * 
	 * @param squadId
	 *            the new squad id
	 */
	public void setSquadId(int squadId) {
		this.squadId = squadId;
		updateObservers();
	}

	/**
	 * Gets the start nr.
	 * 
	 * @return the start nr
	 */
	public int getStartNr() {
		return startNr;
	}

	/**
	 * Sets the start nr. but its not allowed to change it, thats why it does
	 * nothing, but we need it for JPA
	 * 
	 * @param startNr
	 *            the new start nr
	 */
	public void setStartNr(int startNr) {
		this.startNr= startNr;
		updateObservers();
	}

	/**
	 * Gets the prg class.
	 * 
	 * @return the prg class
	 */
	public String getPrgClass() {
		return prgClass;
	}

	/**
	 * Sets the prg class.
	 * 
	 * @param prgClass
	 *            the new prg class
	 */
	public void setPrgClass(String prgClass) {
		this.prgClass = prgClass;
		updateObservers();
	}

	/**
	 * Gets the year of birth.
	 * 
	 * @return the year of birth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 * Sets the year of birth.
	 * 
	 * @param yearOfBirth
	 *            the new year of birth
	 */
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
		updateObservers();
	}

	/**
	 * Gets the marks.
	 * 
	 * @return the marks
	 */
	public Map<DeviceType, Mark> getMarks() {
		return marks;
	}

	/**
	 * Sets the marks.
	 * 
	 * @param marks
	 *            the marks
	 */
	public void setMarks(Map<DeviceType, Mark> marks) {
		this.marks = marks;
		updateObservers();
	}

	/**
	 * Gets the association.
	 * 
	 * @return the association
	 */
	public String getAssociation() {
		return association;
	}

	/**
	 * Sets the association.
	 * 
	 * @param association
	 *            the new association
	 */
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
	 * Adds a mark to a map containing marks.
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

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the mark.
	 * 
	 * @param deviceType
	 *            the device type
	 * @return the mark
	 */
	public Mark getMark(DeviceType deviceType) {
		return marks.get(deviceType);
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
