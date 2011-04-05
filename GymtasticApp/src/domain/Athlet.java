package domain;


public class Athlet extends Person {
    private final int athletId;
    private String prgClass;
    private int yearOfBirth;
    private Association verein;
    private Evaluation eval;
    private int squadID;

    public Athlet(int squadID, int athletId, String prgClass, String firstName, String lastName, String adresse,
	    int yearOfBirth, Association verein) {
	super(firstName, lastName, adresse);
	this.squadID = squadID;
	this.athletId = athletId;
	this.prgClass = prgClass;
	this.yearOfBirth = yearOfBirth;
	this.verein = verein;
	eval = new Evaluation();
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

    public Association getVerein() {
	return verein;
    }

    public void setVerein(Association verein) {
	this.verein = verein;
    }

    public Evaluation getEval() {
	return eval;
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
