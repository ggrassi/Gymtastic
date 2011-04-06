package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import control.DBConnection;

public class Competition {

    private String prgClass;
    private List<Squad> squads = new ArrayList<Squad>();
    private RoundAllocation roundalloc;
    private String name;
    private String description;
    private GregorianCalendar start;
    
    public Competition(String name, GregorianCalendar start, String description) {
	this.name = name;
	this.start = start;
	this.description = description;
    }

    public void addSquad( Squad squad){
	squads.add(squad);
    }
    
    public void importAllSquads(){
    	
    }
    
 

}
