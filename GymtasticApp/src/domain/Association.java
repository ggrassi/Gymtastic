package domain;

import javax.persistence.Embeddable;

@Embeddable
public class Association {

	private String name;
	private String location;
	
	public Association() {
		super();
	}


	public Association(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	
	
}