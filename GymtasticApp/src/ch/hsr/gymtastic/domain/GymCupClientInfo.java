package ch.hsr.gymtastic.domain;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class GymCupClientInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;

	private String location;
	private String name;
	private DeviceType deviceType;

	public GymCupClientInfo(String name, String location,
			GregorianCalendar startDate, GregorianCalendar endDate, DeviceType deviceType) {
		this.name = name;
		this.location = location;
		this.setDeviceType(deviceType);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}
}
