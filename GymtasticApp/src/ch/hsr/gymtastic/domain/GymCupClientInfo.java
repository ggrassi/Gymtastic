package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * The Class GymCupClientInfo holds the start- and enddate, 
 * the locaation and its deviceType for the Client.
 */
public class GymCupClientInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private String location;
	private String name;
	private DeviceType deviceType;

	/**
	 * Instantiates a new gym cup client info.
	 *
	 * @param name the name
	 * @param location the location
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param deviceType the device type
	 */
	public GymCupClientInfo(String name, String location,
			GregorianCalendar startDate, GregorianCalendar endDate, DeviceType deviceType) {
		this.name = name;
		this.location = location;
		this.setDeviceType(deviceType);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public GregorianCalendar getEndDate() {
		return endDate;
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
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the device type.
	 *
	 * @param deviceType the new device type
	 */
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * Gets the device type.
	 *
	 * @return the device type
	 */
	public DeviceType getDeviceType() {
		return deviceType;
	}
}
