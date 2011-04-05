package domain;

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

<<<<<<< HEAD
@Entity
public class Squad{
=======
    private static final long serialVersionUID = -4834032781011953418L;
    private List<Athlet> athlets;
    private int squadId;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int squadId;
	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("athleteId ASC")
	private List<Athlet> athlets;

<<<<<<< HEAD
	public Squad() {
		super();
=======
    public int getId() {
	return squadId;
    }
    
    public int getSquadSize(){
	return athlets.size();
    }
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e

	}

	public Squad(int squadId) {
		super();
		this.squadId=squadId;
		athlets = new LinkedList<Athlet>();
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

	public void addAthlet(Athlet athlet) {
		athlet.setSquad(this);
		athlets.add(athlet);

	}

}
