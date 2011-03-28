package domain;

public class Person {
	private String firstName = null;
	private String lastName = null;
	private String adresse = null;
	
	
	public Person(String name, String vorname) {
		super();
		this.firstName = name;
		this.lastName = vorname;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + firstName + ", vorname=" + lastName + ", adresse="
				+ adresse + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	
	protected String getName() {
		return firstName;
	}
	protected void setName(String name) {
		this.firstName = name;
	}
	protected String getVorname() {
		return lastName;
	}
	protected void setVorname(String vorname) {
		this.lastName = vorname;
	}
	protected String getAdresse() {
		return adresse;
	}
	protected void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
