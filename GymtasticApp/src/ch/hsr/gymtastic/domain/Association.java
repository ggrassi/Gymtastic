package ch.hsr.gymtastic.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

// TODO: Auto-generated Javadoc
/**
 * The Class Association holds the Name and Location of the Athletes Association
 */
@Embeddable
public class Association implements Serializable {

    private static final long serialVersionUID = -3461109808214362308L;
    private String name;
    private String location;

    /**
     * Instantiates a new association.
     * 
     * @param name
     *            the name
     * @param location
     *            the location
     */
    public Association(String name, String location) {
	super();
	this.name = name;
	this.location = location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return name;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the location.
     * 
     * @return the location
     */
    public String getLocation() {
	return location;
    }

    /**
     * Sets the location.
     * 
     * @param location
     *            the new location
     */
    public void setLocation(String location) {
	this.location = location;
    }

}
