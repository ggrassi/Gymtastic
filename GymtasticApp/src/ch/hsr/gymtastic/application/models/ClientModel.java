package ch.hsr.gymtastic.application.models;

import java.util.Observable;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;

public class ClientModel extends Observable {
	private Athlete athlete;
	private DeviceType deviceType;

	public ClientModel() {
		athlete = new Athlete();
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete actualAthlete) {
		this.athlete = actualAthlete;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

}
