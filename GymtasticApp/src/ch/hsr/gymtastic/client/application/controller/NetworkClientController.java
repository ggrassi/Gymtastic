package ch.hsr.gymtastic.client.application.controller;

import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.technicalServices.network.RMIClient;
import ch.hsr.gymtastic.technicalServices.network.exceptions.ConnectionFailedException;
import ch.hsr.gymtastic.technicalServices.network.exceptions.TransmissionException;

/**
 * The Class NetworkClientController.
 */
public class NetworkClientController implements Observer {

	private RMIClient rmiClient;
	private SquadController squadController;
	private GymCupInfoController gymCupInfoController;
	private CompetitionInfoController competitionInfoController;

	/**
	 * Instantiates a new network client controller.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public NetworkClientController(){
		rmiClient = new RMIClient();
		rmiClient.addObserver(this);
	}

	/**
	 * Sets the server ip.
	 * 
	 * @param serverIP
	 *            the new server ip
	 */
	public void setServerIP(String serverIP) {
		rmiClient.setServerIP(serverIP);
	}

	/**
	 * Connects to the client which is registred under a certain DeviceType
	 * 
	 * @param deviceType
	 *            the device type
	 * @throws ConnectionFailedException
	 *             the connection failed exception
	 */
	public void connect(DeviceType deviceType) throws ConnectionFailedException {
		try {
			rmiClient.connect(deviceType);
		} catch (Exception e) {
			throw new ConnectionFailedException();
		}
	}

	/**
	 * Disconnects all Clients.
	 * 
	 * @throws TransmissionException
	 *             the transmission exception
	 */
	public void disconnect() throws TransmissionException {
		try {
			rmiClient.disconnect();
		} catch (RemoteException e) {
			throw new TransmissionException();
		}
	}

	/**
	 * Sets the squad controller.
	 * 
	 * @param squadController
	 *            the new squad controller
	 */
	public void setSquadController(SquadController squadController) {
		this.squadController = squadController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof RoundInfo) {
			squadController.setRoundInfo(arg);
		} else if (arg instanceof GymCupClientInfo) {
			gymCupInfoController.setGymCupInfo(arg);
		} else if (arg instanceof CompetitionInfo) {
			competitionInfoController.setComeptitionInfo(arg);
		}
	}

	/**
	 * Sets the gym cup info controller.
	 * 
	 * @param gymCupInfoController
	 *            the new gym cup info controller
	 */
	public void setGymCupInfoController(
			GymCupInfoController gymCupInfoController) {
		this.gymCupInfoController = gymCupInfoController;
	}

	/**
	 * Sets the round info controller.
	 * 
	 * @param competitionInfoController
	 *            the new round info controller
	 */
	public void setRoundInfoController(
			CompetitionInfoController competitionInfoController) {
		this.competitionInfoController = competitionInfoController;
	}

	/**
	 * Sends an object from the client to the server.
	 * 
	 * @param obj
	 *            the obj
	 * @throws ConnectException
	 *             the connect exception
	 */
	public void sendObjectToServer(Serializable obj)
			throws TransmissionException {
		rmiClient.sendObjectToServer(obj);
	}
}
