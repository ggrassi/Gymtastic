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

public class NetworkClientController implements Observer {

	private RMIClient rmiClient;
	private SquadController squadController;
	private GymCupInfoController gymCupInfoController;
	private CompetitionInfoController competitionInfoController;

	public NetworkClientController() throws Exception {
		rmiClient = new RMIClient();
		rmiClient.addObserver(this);
	}

	public void setServerIP(String serverIP) {
		rmiClient.setServerIP(serverIP);
	}

	public void connect(DeviceType deviceType) throws ConnectionFailedException {
		try {
			rmiClient.connect(deviceType);
		} catch (Exception e) {
			throw new ConnectionFailedException();
		}
	}

	public void disconnect() throws TransmissionException {
		try {
			rmiClient.disconnect();
		} catch (RemoteException e) {
			throw new TransmissionException();
		}
	}

	public void setSquadController(SquadController squadController) {
		this.squadController = squadController;
	}

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

	public void setGymCupInfoController(
			GymCupInfoController gymCupInfoController) {
		this.gymCupInfoController = gymCupInfoController;
	}

	public void setRoundInfoController(CompetitionInfoController competitionInfoController) {
		this.competitionInfoController = competitionInfoController;
	}

	public void sendObjectToServer(Serializable obj) throws ConnectException {
		rmiClient.sendObjectToServer(obj);
		System.out.println("Sent Squad to Server!");
	}
}
