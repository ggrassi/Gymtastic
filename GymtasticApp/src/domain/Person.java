package domain;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

	private String vorname;
	private String nachname;
	private String adresse;

	public Person() {
		super();
=======
public class Person {
	private String firstName = null;
	private String lastName = null;
	private String adresse = null;
	
	
	public Person(String firstName, String lastName, String adresse) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
	}

	

	public Person(String vorname, String nachname, String adresse) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
	}

<<<<<<< HEAD


	public String getVorname() {
		return vorname;
	}



	public void setVorname(String vorname) {
		this.vorname = vorname;
	}



	public String getNachname() {
		return nachname;
	}



	public void setNachname(String nachname) {
		this.nachname = nachname;
=======
	
	public String getFirstName() {
		return firstName;
	}
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	protected void setLastName(String lastName) {
		this.lastName = lastName;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	
}
