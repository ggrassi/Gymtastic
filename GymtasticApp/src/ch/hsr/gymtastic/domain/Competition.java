package ch.hsr.gymtastic.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.hsr.gymtastic.application.controller.RoundAllocation;
@Entity
public class Competition {
	@OneToMany(cascade = CascadeType.ALL)
    private List<Squad> squads = new ArrayList<Squad>();
    private String name;
    private String description;
    private GregorianCalendar start;
    private RoundAllocation roundAllocation;

    public Competition(String name, GregorianCalendar start, String description) {
	this.name = name;
	this.start = start;
	this.description = description;
    }

    public void importAllSquads() {
    	
    }

	public List<Squad> getSquads() {
		return squads;
	}

	public void setSquads(List<Squad> squads) {
		this.squads = squads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public RoundAllocation getRoundAllocation() {
		return roundAllocation;
	}

	public void setRoundAllocation(RoundAllocation roundAllocation) {
		this.roundAllocation = roundAllocation;
	}

}
