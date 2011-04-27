package ch.hsr.gymtastic.application.controller;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;

public class NetworkServerController extends Observable implements Observer {

	private RMIServer rmiServer;
	private Squad squad;
	private ClientAllocator clientsAllocated = new ClientAllocator();

	public NetworkServerController() throws ConnectException {
		try {
			rmiServer = new RMIServer();
		} catch (RemoteException e) {
			throw new ConnectException();
		}
		rmiServer.addObserver(this);
	}

	public void updateClient(RMIClientInterface client, Squad squad)
			throws ConnectException {
		try {
			client.uploadSquadToClient(squad);
		} catch (RemoteException e) {
			throw new ConnectException();
		}

	}

	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return rmiServer.getClientsWaitingForAllocation();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.squad = (Squad) arg;
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
		
	}

}
