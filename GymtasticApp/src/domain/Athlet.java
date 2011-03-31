package domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Athlet extends Person {
    @Embedded
    private Association association;
    @OneToOne
    private Evaluation evaluation;
    @Id
    private int squadID;
    @Id
    private int athletID;
    @Id
    private String prgClass;
    @Id
    private int yearOfBirth;

    public Athlet(int squadID, int athletID, String prgClass, String vorname, String name, String adresse,
	    int yearOfBirth, Association verein) {
	super(name, vorname, adresse);

	this.squadID = squadID;
	this.athletID = athletID;
	this.prgClass = prgClass;
	this.yearOfBirth = yearOfBirth;
	this.association = verein;
	evaluation = new Evaluation();
    }

    public String getPrgClass() {
	return prgClass;
    }

    public int getYearOfBirth() {
	return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
	this.yearOfBirth = yearOfBirth;
    }

    public Association getAssociation() {
	return association;
    }

    public void setAssociation(Association verein) {
	this.association = verein;
    }

    public Evaluation getEval() {
	return evaluation;
    }

    public int getAthletId() {
	return athletID;
    }

    public int getSquadID() {
	return squadID;
    }

    public void setSquadID(int squadId) {
	this.squadID = squadId;
    }

}
