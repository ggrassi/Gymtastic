package ch.hsr.gymtastic.server.application.controller.cupmanagement;

import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;

/**
 * The Class NetworkServerController holds an RMIServer and a ClientAllocator.
 */
public class NetworkServerController extends Observable implements Observer {

	private RMIServer rmiServer;
	private ClientAllocator clientsAllocater = new ClientAllocator();

	/**
	 * Instantiates a new network server controller.
	 * 
	 * @throws ConnectException
	 *             the connect exception
	 */
	public NetworkServerController() throws ConnectException {
		try {
			rmiServer = new RMIServer("Gymtastic");
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectException();
		}
		rmiServer.addObserver(this);
	}

	/**
	 * Sends the object to all allocated clients.
	 * 
	 * @param obj
	 *            the obj
	 * @throws ConnectException
	 *             the connect exception
	 */
	public void sendObjectToAllClients(Serializable obj)
			throws ConnectException {
		try {
			for (RMIClientInterface client : clientsAllocater
					.getAllocatedClients()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object obj) {
		updateObservers(obj);
	}

	private void updateObservers(Object obj) {
		setChanged();
		notifyObservers(obj);

	}

	/**
	 * Sends the object the client.
	 * 
	 * @param client
	 *            the client
	 * @param obj
	 *            the obj
	 * @throws ConnectException
	 *             the connect exception
	 */
	public void sendObjectToClient(RMIClientInterface client, Serializable obj)
			throws ConnectException {
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

	public void removeWaitingClients() {
		rmiServer.getClientsWaitingForAllocation().clear();
		updateObservers(null);

	}

}
