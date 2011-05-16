package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Vector;

import ch.hsr.gymtastic.domain.DeviceType;

public class RMIServer extends Observable implements RMIServerInterface {

	Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();

	public RMIServer() throws RemoteException {
		RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject
				.exportObject(this, 0);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("Gymtastic", stub);
	}

	@Override
	public void addClient(RMIClientInterface client, Serializable deviceType)
			throws RemoteException, ServerNotActiveException {
		clientsWaitingForAllocation.add(new ClientInformation(client,
				RemoteServer.getClientHost(), (DeviceType) deviceType));
		System.out.println("Client added");
		updateObservers();

	}

	@Override
	public void removeClient(RMIClientInterface client) throws RemoteException {

		/*
		 * TODO
		 */
		
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
		
	}

	@Override
	public void uploadObjectToServer(Serializable object) throws RemoteException {
	updateObservers(object);

	}

	private void updateObservers(Serializable object) {
		setChanged();
		notifyObservers(object);
	}

	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return clientsWaitingForAllocation;
	}
}
