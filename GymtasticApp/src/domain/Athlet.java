package domain;

import java.util.UUID;

public class Athlet extends Person {
    private int squadID;
    UUID athletId = null;
    private Verein verein;
    private int yearOfBirth;
    private Evaluation eval;

    public Athlet(String firstName, String lastName) {
	super(firstName, lastName);
    }

    public Athlet(String firstName, String lastName, int yearOfBirth, int squadId) {
	super(firstName, lastName);
	this.yearOfBirth = yearOfBirth;
	this.squadID = squadId;
	this.athletId = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((eval == null) ? 0 : eval.hashCode());
	result = prime * result + squadID;
	result = prime * result + ((verein == null) ? 0 : verein.hashCode());
	result = prime * result + yearOfBirth;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Athlet other = (Athlet) obj;
	if (eval == null) {
	    if (other.eval != null)
		return false;
	} else if (!eval.equals(other.eval))
	    return false;
	if (squadID != other.squadID)
	    return false;
	if (verein == null) {
	    if (other.verein != null)
		return false;
	} else if (!verein.equals(other.verein))
	    return false;
	if (yearOfBirth != other.yearOfBirth)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Athlet [squadID=" + squadID + ", verein=" + verein + ", name=" + super.getName() + "]";
    }

    public int getSquadID() {
	return squadID;
    }

    public void setSquadID(int squadID) {
	this.squadID = squadID;
    }

    public Verein getVerein() {
	return verein;
    }

    public void setVerein(Verein verein) {
	this.verein = verein;
    }

    public int getYearOfBirth() {
	return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
	this.yearOfBirth = yearOfBirth;
    }

    public Evaluation getEval() {
	return eval;
    }

    public void setEval(Evaluation eval) {
	this.eval = eval;
    }

    public UUID getId() {
	return athletId;
    }

}
