package ch.hsr.gymtastic.domain;

import java.io.Serializable;

/**
 * The Enum DeviceType contains the possible Excercise DeviceTypes. Each Type
 * represents an Integer.
 */
public enum DeviceType implements Serializable {

    FLOOR_EXCERCISE(0), POMMEL_HORSE(1), STILL_RINGS(2), VAULT(3), PARALLEL_BARS(4), HIGH_BAR(5);

    public int index;

    /**
     * Instantiates a new device type.
     * 
     * @param index
     *            the index
     */
    DeviceType(int index) {
	this.index = index;
    }

    /**
     * Gets the index.
     * 
     * @return the index
     */
    public int getIndex() {
	return index;
    }

    /**
     * Gets the appropriate DeviceType in combination with his Index.
     * 
     * @param index
     *            the index
     * @return the device
     */
    public DeviceType getDevice(int index) {
	switch (index) {
	case 0:
	    return FLOOR_EXCERCISE;
	case 1:
	    return POMMEL_HORSE;
	case 2:
	    return STILL_RINGS;
	case 3:
	    return VAULT;
	case 4:
	    return PARALLEL_BARS;
	case 5:
	    return HIGH_BAR;
	default:
	    return null;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    public String toString() {
	switch (index) {
	case 0:
	    return "Boden";
	case 1:
	    return "Pferd";
	case 2:
	    return "Ringe";
	case 3:
	    return "Sprung";
	case 4:
	    return "Barren";
	case 5:
	    return "Reck";
	default:
	    return "";

	}

    }
}
