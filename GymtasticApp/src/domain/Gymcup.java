package domain;

import java.util.*;

public class Gymcup {
    
    private List<Competition> competitions = new ArrayList<Competition>();
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
    
}
