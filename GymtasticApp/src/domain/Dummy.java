package domain;

import java.io.Serializable;

public class Dummy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Dummy(String name)
	{
		this.name = name;
		System.out.println("Dummy created with name: " + this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
