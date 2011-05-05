package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends Observable implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3643070978985690281L;
    private String firstName;
    private String lastName;
    private String address;

    public Person() {
	super();

    }

    public Person(String firstName, String lastName, String address) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getAddress() {
	return this.address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

}