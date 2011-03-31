package domain;

import java.util.ArrayList;
import java.util.List;

public class Competition {

    private String prgClass;
    private List<Squad> squads = new ArrayList<Squad>();
    private RoundAllocation roundalloc;
    
    public void addSquad( Squad squad){
	squads.add(squad);
    }
    
    public void importAllSquads(){
	
    }
    
 

}
