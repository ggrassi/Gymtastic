package ch.hsr.gymtastic.application.models;

import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

public class ClientModel extends Observable implements Observer {
	private Athlete athlete;
	private Squad squad;
	private DeviceType deviceType;
	private SquadController squadController;

	public ClientModel(SquadController squadController) {
		this.squadController = squadController;
		this.squadController.addObserver(this);
		athlete = new Athlete();
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public SquadController getSquadController() {
		return squadController;
	}

	public Squad getSquad() {
		return squad;
	}

	@Override
	public void update(Observable o, Object arg) {
		squad = getSquadController().getSquad();
		athlete = getSquadController().getNextAthlete();
		updateObservers();

	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}
}
