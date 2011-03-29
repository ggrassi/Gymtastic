package domain;

public class Athlet extends Person {
	private int yearOfBirth;
	private int squadID;
	private Verein verein;
	private Evaluation eval;
	
	public Athlet(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public Athlet(String firstName, String lastName, int yearOfBirth, int squadId) {
		super(firstName, lastName);
		this.yearOfBirth = yearOfBirth;
		this.squadID = squadId;
	
	}

	
	
}
