package domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class Squad implements Serializable {

    private static final long serialVersionUID = -4834032781011953418L;
    private Set<Athlet> athlets;
    private final int squadId;

    public Squad(int squadId) {
	this.squadId = squadId;
	athlets = new TreeSet<Athlet>();
    }

    public int getId() {
	return squadId;
    }

    public void addAthlete(Athlet athlet) {
	if (athlet != null && athlet.getSquadID() == squadId) {
	    athlets.add(athlet);
	}
    }
    
    public Athlet getAthlete(){
	return null;
    }

    @Override
    public String toString() {
	return "Squad [squadId=" + squadId + "]";
    }
    
}
