package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class AthletID {
    @Column(name = "athletID", nullable = false)
    @Id public int athletID;
    @Column(name = "squadID", nullable = false)
    @Id public int squadID;

    public AthletID(int athletId, int squadID) {
	this.athletID = athletId;
	this.squadID = squadID;
    }

    public AthletID() {
	super();
    }
}