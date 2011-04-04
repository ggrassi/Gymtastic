package domain;

import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;

public class Squad implements Serializable {

    private static final long serialVersionUID = -4834032781011953418L;
    private List<Athlet> athlets;
    private int squadId;

    public Squad(int squadId) {
	this.squadId = squadId;
	athlets = new LinkedList<Athlet>();
    }

    public int getId() {
	return squadId;
    }
    
    public int getSquadSize(){
	return athlets.size();
    }

    public void addAthlete(Athlet athlet) {
	if (athlet != null && athlet.getSquadID() == squadId) {
	    athlets.add(athlet);
	}
    }
    
    public Athlet getAthlete(int pos){
	if(pos < athlets.size()){
	    return athlets.get(pos);
	}else{
	    return null;
	}
	
    }
    
    public void removeAthlete(Athlet athlet){
	athlets.remove(athlet);
    }

    @Override
    public String toString() {
	return "Squad [squadId=" + squadId + "]";
    }
    
}
