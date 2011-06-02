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

/**
 * The Class RMIServer represents all Methods which can be selected from the
 * RMIServer
 */
public class RMIServer extends Observable implements RMIServerInterface {

	private Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();

	/**
	 * Instantiates a new RMI server.
	 * 
	 * @throws RemoteException
	 *             the remote exception
	 */
	public RMIServer() {

	}

	/**
	 * Instantiates a new RMI server.
	 * 
	 * @throws RemoteException
	 *             the remote exception
	 */
	public RMIServer(String name) throws RemoteException {
		RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject
				.exportObject(this, 0);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind(name, stub);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.gymtastic.technicalServices.network.RMIServerInterface#addClient
	 * (ch.hsr.gymtastic.technicalServices.network.RMIClientInterface,
	 * java.io.Serializable)
	 */
	@Override
	public void addClient(RMIClientInterface client, Serializable deviceType)
			throws RemoteException, ServerNotActiveException {

		clientsWaitingForAllocation.add(new ClientInformation(client,
				RemoteServer.getClientHost(), deviceType));
		updateObservers();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.gymtastic.technicalServices.network.RMIServerInterface#removeClient
	 * (ch.hsr.gymtastic.technicalServices.network.RMIClientInterface)
	 */
	@Override
	public void removeClient(RMIClientInterface client) throws RemoteException {
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.gymtastic.technicalServices.network.RMIServerInterface#
	 * uploadObjectToServer(java.io.Serializable)
	 */
	@Override
	public void uploadObjectToServer(Serializable object)
			throws RemoteException {
		updateObservers(object);

	}

	/**
	 * Update observers.
	 * 
	 * @param object
	 *            the object
	 */
	private void updateObservers(Serializable object) {
		setChanged();
		notifyObservers(object);
	}

	/**
	 * Gets the clients waiting for allocation.
	 * 
	 * @return the clients waiting for allocation
	 */
	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return clientsWaitingForAllocation;
	}
}
