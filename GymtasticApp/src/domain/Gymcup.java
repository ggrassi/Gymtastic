package domain;

import java.util.*;

public class Gymcup {
    
    private List<Competition> competitions = new ArrayList<Competition>();
    private Map<Integer, Squad> squads;
    private ClientAllocation clientAlloc;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String sponsers;
    private String ort;
    private String name;
    
    public Gymcup(String name, String ort){
	this.name = name;
	this.ort = ort;
    }
    
    public void addCompetition(Competition competition){
	competitions.add(competition);
    }
    
    public void removeCompetition(Competition competition){
	competitions.remove(competition);
    }
    
    public void addSquads( Map<Integer, Squad> squads){
	this.squads = squads;
    }
    
    public List<Competition> getCompetitions(){
	return competitions;
    }
    
    public Map<Integer, Squad> getSquads(){
	return squads;
    }
    
    public String toString(){
	return name;	
    }
    
}
