package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.hsr.gymtastic.server.application.controller.RoundAllocator;

@Entity
public class Competition extends Observable {
	@OneToMany(cascade = CascadeType.ALL)
	private List<Squad> squads = new ArrayList<Squad>();
	private String description;
	private String startTime;
	private String endTime;
	private GregorianCalendar date;
	private String programClass;
	private RoundAllocator roundAllocation;

	public Competition(String description, GregorianCalendar date,
			String startTime, String endTime, String programClass) {
		this.description = description;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.programClass = programClass;
	}

	public void importAllSquads() {

	}

	public List<Squad> getSquads() {
		return squads;
	}

	public void setSquads(List<Squad> squads) {
		this.squads = squads;
		updateObservers();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		updateObservers();
	}

	public RoundAllocator getRoundAllocation() {
		return roundAllocation;
	}

	public void setRoundAllocation(RoundAllocator roundAllocation) {
		this.roundAllocation = roundAllocation;
		updateObservers();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
		updateObservers();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
		updateObservers();
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
		updateObservers();
	}

	public String getProgramClass() {
		return programClass;
	}

	public void setProgramClass(String programClass) {
		this.programClass = programClass;
	}

	public void addSquad(Squad squad) {
		squads.add(squad);
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return description;
	}

}
