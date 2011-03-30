package domain;


public class Athlet extends Person {
    private final int athletId;
    private String prgClass;
    private int yearOfBirth;
    private Verein verein;
    private Evaluation eval;
    private int squadID;

    public Athlet(int squadID, int athletId, String prgClass, String name, String vorname, String adresse,
	    int yearOfBirth, Verein verein) {
	super(name, vorname, adresse);
	this.squadID = squadID;
	this.athletId = athletId;
	this.prgClass = prgClass;
	this.yearOfBirth = yearOfBirth;
	this.verein = verein;
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

    public Verein getVerein() {
	return verein;
    }

    public void setVerein(Verein verein) {
	this.verein = verein;
    }

    public Evaluation getEval() {
	return eval;
    }

    public void setEval(Evaluation eval) {
	this.eval = eval;
    }

    public int getAthletId() {
	return athletId;
    }

    public int getSquadID() {
	return squadID;
    }
    
    public void setSquadID(int squadId) {
	this.squadID = squadId;
    }

}
