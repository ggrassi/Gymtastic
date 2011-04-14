package ch.hsr.gymtastic.application.controller;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.RMIClient;
import ch.hsr.gymtastic.technicalServices.network.RMIServerInterface;

public class NetworkClientController extends Observable implements Observer {

	private RMIClient rmiClient;
	private RMIServerInterface rmiServer;
	private Squad squad;

	public NetworkClientController() throws Exception {
		rmiClient = new RMIClient();
	}

	public void setServerIP(String serverIP) {
		rmiClient.setServerIP(serverIP);
	}

	public void updateServer() throws ConnectException {
		try {
			rmiServer.uploadSquadToServer(squad);
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

	@Override
	public void update(Observable o, Object arg) {
		this.squad = (Squad) arg;

	}

	public Squad getSquad() {
		return squad;
	}

}
