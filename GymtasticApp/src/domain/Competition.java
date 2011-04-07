package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Competition {

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

    public void addSquad(Squad squad) {
	squads.add(squad);
    }

    public void importAllSquads() {

    }

    public List<Squad> getSquads() {
	return squads;
    }

    public void addRoundAllocation(RoundAllocation roundAllocation) {
	this.roundAllocation = roundAllocation;	
    }
    
    public RoundAllocation getRoundAllocation(){
	return roundAllocation;
    }

}
