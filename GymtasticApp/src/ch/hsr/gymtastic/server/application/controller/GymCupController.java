package ch.hsr.gymtastic.server.application.controller;

import java.net.ConnectException;
import java.util.Observable;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

public class GymCupController extends Observable {
	private GymCup gymCup;
	private CompetitionController competitionController;
	private ClientAllocator clientAllocator;
	private NetworkServerController networkController;

	public GymCupController() {
		gymCup = new GymCup();
		try {
			networkController = new NetworkServerController();
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		competitionController = new CompetitionController(networkController);
		this.clientAllocator = networkController.getClientAllocater();
	}

	public void setExistingGymcup() {
		setGymCup(DBController.getExistingGymCup());
		updateObservers();
	}

	public void setGymCup(GymCup gymCup) {
		this.gymCup = gymCup;
		this.competitionController.setGymCup(gymCup);
		updateObservers();
	}

	public GymCup getGymCup() {
		return gymCup;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

	public CompetitionController getCompetitionController() {
		return competitionController;
	}

	public ClientAllocator getClientAllocator() {
		return clientAllocator;
	}

	public NetworkServerController getNetworkController() {
		return networkController;
	}

	public void sendGymCupInfosToClients() {
		for (DeviceType deviceType : DeviceType.values()) {
			ClientInformation clientInformation = clientAllocator
					.getClientInformation(deviceType);
			GymCupClientInfo gymCupInfo = getGymCupInfo(deviceType);
			if (clientInformation != null) {
				try {
					networkController.sendObjectToClient(
							clientInformation.getStub(), gymCupInfo);
				} catch (ConnectException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private GymCupClientInfo getGymCupInfo(DeviceType deviceType) {
		GymCupClientInfo gymCupInfo = new GymCupClientInfo(gymCup.getName(),
				gymCup.getLocation(), gymCup.getStartDate(),
				gymCup.getEndDate(), deviceType);
		return gymCupInfo;
	}

}
