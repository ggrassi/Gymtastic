package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Squad implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3240358878521229793L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int squadId;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("athleteId ASC")
    private List<Athlet> athlets;

    public Squad() {
	super();
	athlets = new LinkedList<Athlet>();
    }

    public Squad(int squadId) {
	super();
	this.squadId = squadId;
	athlets = new LinkedList<Athlet>();
    }

    public void addAthlet(Athlet athlet) {
	athlets.add(athlet);
    }

    public int getSquadSize() {
	return athlets.size();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getSquadId() {
	return squadId;
    }

    public Collection<Athlet> getAthlets() {
	return athlets;
    }

    public void setAthlets(List<Athlet> athlets) {
	this.athlets = athlets;
    }

    public Athlet getAthlete(int index) {
	return athlets.get(index);
    }

}