package ch.hsr.gymtastic.server.application.controller;

import java.net.ConnectException;
import java.util.List;
import java.util.Observable;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;
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
		competitionController = new CompetitionController(networkController,
				gymCup);
		this.clientAllocator = networkController.getClientAllocater();
	}

	public void setExistingGymcup() {
		DBConnection db = new DBConnection();
		System.out.println(DBConnection.getPath());
		TypedQuery<GymCup> query = db.getEm().createQuery(
				"SELECT p FROM GymCup p", GymCup.class);
		List<GymCup> result = query.getResultList();
		if (result.size() == 1) {
			int first = 0;
			this.gymCup = result.get(first);
		}

		db.commit();
		db.closeConnection();
		setGymCup(gymCup);
		updateObservers();
	}

	public void setGymCup(GymCup gymCup) {
		this.gymCup = gymCup;
		competitionController.setGymCup(gymCup);
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
