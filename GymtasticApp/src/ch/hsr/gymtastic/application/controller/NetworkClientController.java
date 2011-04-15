package ch.hsr.gymtastic.application.controller;

import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.RMIClient;
import ch.hsr.gymtastic.technicalServices.network.RMIServerInterface;

public class NetworkClientController implements Observer {

	private RMIClient rmiClient;
	private RMIServerInterface rmiServer;
	private SquadController squadController;

	public NetworkClientController() throws Exception {
		rmiClient = new RMIClient();
		rmiClient.addObserver(this);
	}

	public void setServerIP(String serverIP) {
		rmiClient.setServerIP(serverIP);
	}

	public void updateServer(Serializable object) throws ConnectException {
		try {
			rmiServer.uploadSquadToServer(object);
		} catch (RemoteException e) {
			throw new ConnectException();
		}
	}

	public void connect(DeviceType deviceType) throws Exception {
		rmiServer = rmiClient.connect(deviceType);

	}

	public void disconnect() throws RemoteException {
		rmiClient.disconnect();
	}

	public void setSquadController(SquadController squadController) {
		this.squadController = squadController;
	}

	@Override
	public void update(Observable o, Object arg) {
		squadController.setSquad(arg);

	}

}
