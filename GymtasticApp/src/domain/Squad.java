package domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Squad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private final int squadId;
    @OneToMany
    private List<Athlet> athlets;

    public Squad(int squadId) {
	this.squadId = squadId;
	athlets = new LinkedList<Athlet>();
    }

    public int getId() {
	return squadId;
    }

    public void addAthlete(Athlet athlet) {
	if (athlet != null && athlet.getSquadID() == squadId) {
	    athlets.add(athlet);
	}
    }

    public Athlet getAthlete(int pos) {
	if (pos < athlets.size()) {
	    return athlets.get(pos);
	} else {
	    return null;
	}

    }

    public void removeAthlete(Athlet athlet) {
	athlets.remove(athlet);
    }

    @Override
    public String toString() {
	return "Squad [squadId=" + squadId + "]";
    }

}
