package domain;

import java.io.Serializable;

public class Dummy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int eNote;
	private int dNote;
	
	public Dummy(String name, int eNote, int dNote) {
		super();
		this.name = name;
		this.eNote = eNote;
		this.dNote = dNote;
	}
	
	public Dummy(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void seteNote(int eNote) {
		this.eNote = eNote;
	}

	public int geteNote() {
		return eNote;
	}

	public void setdNote(int dNote) {
		this.dNote = dNote;
	}

	public int getdNote() {
		return dNote;
	}

	@Override
	public String toString() {
		return "Dummy [Name: "+ getName() +" E-Note: "+ geteNote() +" D-Note: "+ getdNote() +"]";
	}
	
	
	
	

}
