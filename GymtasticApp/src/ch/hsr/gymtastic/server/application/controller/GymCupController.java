package ch.hsr.gymtastic.server.application.controller;

import java.net.ConnectException;
import java.util.Observable;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

/**
 * The Class GymCupController holds the informations about the GymCup,
 * ClientAllocator, the Competition- and NetworkController.
 */
public class GymCupController extends Observable {

	private GymCup gymCup;
	private CompetitionController competitionController;
	private ClientAllocator clientAllocator;
	private NetworkServerController networkController;

	/**
	 * Instantiates a new gym cup controller.
	 */
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

	/**
	 * Sets the existing gymcup.
	 */
	public void setExistingGymcup() {
		setGymCup(DBController.getActualGymCup());
		updateObservers();
	}

	/**
	 * Sets the gym cup.
	 * 
	 * @param gymCup
	 *            the new gym cup
	 */
	public void setGymCup(GymCup gymCup) {
		this.gymCup = gymCup;
		this.competitionController.setGymCup(gymCup);
		updateObservers();
	}

	/**
	 * Gets the gym cup.
	 * 
	 * @return the gym cup
	 */
	public GymCup getGymCup() {
		return gymCup;
	}

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

	/**
	 * Gets the competition controller.
	 * 
	 * @return the competition controller
	 */
	public CompetitionController getCompetitionController() {
		return competitionController;
	}

	/**
	 * Gets the client allocator.
	 * 
	 * @return the client allocator
	 */
	public ClientAllocator getClientAllocator() {
		return clientAllocator;
	}

	/**
	 * Gets the network controller.
	 * 
	 * @return the network controller
	 */
	public NetworkServerController getNetworkController() {
		return networkController;
	}

	/**
	 * Send gym cup informations to all the active clients.
	 */
	public void sendGymCupInfosToClients() {
		for (DeviceType deviceType : DeviceType.values()) {
			ClientInformation clientInformation = clientAllocator
					.getClientInformation(deviceType);
			GymCupClientInfo gymCupInfo = getGymCupInfo(deviceType);
			if (clientInformation != null) {
				try {
					networkController.sendObjectToClient(clientInformation
							.getStub(), gymCupInfo);
				} catch (ConnectException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Gets the gym cup informations from the actual cup
	 * 
	 * @param deviceType
	 *            the device type
	 * @return the gym cup info
	 */
	private GymCupClientInfo getGymCupInfo(DeviceType deviceType) {
		GymCupClientInfo gymCupInfo = gymCup.getGymCupClientInfo(deviceType);
		return gymCupInfo;
	}

	public void setNetworkController(NetworkServerController object) {
	    networkController = object;
	}

}
