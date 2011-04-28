package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.hsr.gymtastic.application.controller.server.RoundAllocator;

@Entity
public class Competition {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Squad> squads = new ArrayList<Squad>();
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private GregorianCalendar date;
    private String programClass;
    private RoundAllocator roundAllocation;

    public Competition(String description, GregorianCalendar date, GregorianCalendar startTime) {
	this.description = description;
	this.date = date;
	this.startTime = startTime;
    }

    public void importAllSquads() {

    }

    public List<Squad> getSquads() {
	return squads;
    }

    public void setSquads(List<Squad> squads) {
	this.squads = squads;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public RoundAllocator getRoundAllocation() {
	return roundAllocation;
    }

    public void setRoundAllocation(RoundAllocator roundAllocation) {
	this.roundAllocation = roundAllocation;
    }

    public GregorianCalendar getStartTime() {
	return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
	this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
	return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
	this.endTime = endTime;
    }

    public GregorianCalendar getDate() {
	return date;
    }

    public void setDate(GregorianCalendar date) {
	this.date = date;
    }

    public String getProgramClass() {
	return programClass;
    }

    public void setProgramClass(String programClass) {
	this.programClass = programClass;
    }

}
