package ch.hsr.gymtastic.application.controller.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.RMIClient;
import ch.hsr.gymtastic.technicalServices.network.RMIServerInterface;
import ch.hsr.gymtastic.technicalServices.network.exceptions.ConnectionFailedException;
import ch.hsr.gymtastic.technicalServices.network.exceptions.TransmissionException;

public class NetworkClientController implements Observer {

	private RMIClient rmiClient;
	private RMIServerInterface rmiServer;
	private SquadController squadController;
	private GymCupInfoController gymCupInfoController;
	private RoundInfoController roundInfoController;

	public NetworkClientController() throws Exception {
		rmiClient = new RMIClient();
		rmiClient.addObserver(this);
	}

	public void setServerIP(String serverIP) {
		rmiClient.setServerIP(serverIP);
	}

	public void updateServer(Serializable object) throws TransmissionException {
		try {
			rmiServer.uploadSquadToServer(object);
		} catch (RemoteException e) {
			throw new TransmissionException();
		}
	}

	public void connect(DeviceType deviceType) throws ConnectionFailedException {
		try {
			rmiServer = rmiClient.connect(deviceType);
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
		if (arg instanceof Squad) {
			squadController.setSquad(arg);
		} else if (arg instanceof GymCupClientInfo) {
			gymCupInfoController.setGymCupInfo(arg);
		} else if (arg instanceof RoundInfo) {
			roundInfoController.setRoundInfo(arg);
		}
	}

	public void setGymCupInfoController(
			GymCupInfoController gymCupInfoController) {
		this.gymCupInfoController = gymCupInfoController;
	}

	public void setRoundInfoController(RoundInfoController roundInfoController) {
		this.roundInfoController = roundInfoController;
	}
}