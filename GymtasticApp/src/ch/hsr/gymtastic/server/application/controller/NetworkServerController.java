package ch.hsr.gymtastic.server.application.controller;

import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;

public class NetworkServerController extends Observable implements Observer {

	private RMIServer rmiServer;
	private ClientAllocator clientsAllocater = new ClientAllocator();

	public NetworkServerController() throws ConnectException {
		try {
			rmiServer = new RMIServer();
		} catch (RemoteException e) {
			throw new ConnectException();
		}
		rmiServer.addObserver(this);
	}

	public void sendObjectToAllClients(Serializable obj)
			throws ConnectException {
		try {
			for (RMIClientInterface client : clientsAllocater.getAllocatedClients()) {
				if (client != null) {
					client.uploadObjectToClient(obj);
				}
			}
		} catch (RemoteException e) {
			throw new ConnectException();
		}
	}

	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return rmiServer.getClientsWaitingForAllocation();
	}

	@Override
	public void update(Observable o, Object obj) {
		updateObservers(obj);
	}

	private void updateObservers(Object obj) {
		setChanged();
		notifyObservers(obj);

	}

	public void sendObjectToClient(RMIClientInterface client,
			Serializable obj) throws ConnectException {
		if (client != null) {
			try {
				client.uploadObjectToClient(obj);
			} catch (RemoteException e) {
				throw new ConnectException();
			}
		}

	}

	public void setClientsAllocated(ClientAllocator clientsAllocated) {
		this.clientsAllocater = clientsAllocated;
	}

	public ClientAllocator getClientAllocater() {
		return clientsAllocater;
	}

}
