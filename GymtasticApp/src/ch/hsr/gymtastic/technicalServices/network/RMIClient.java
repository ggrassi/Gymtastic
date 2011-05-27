package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

import ch.hsr.gymtastic.technicalServices.network.exceptions.TransmissionException;

/**
 * The Class RMIClient.
 */
public class RMIClient extends Observable implements RMIClientInterface {

	private String serverIP = "localhost";
	private RMIServerInterface rmiServerInterface;

	/**
	 * Instantiates a new RMI client.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public RMIClient() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.gymtastic.technicalServices.network.RMIClientInterface#
	 * uploadObjectToClient(java.io.Serializable)
	 */
	@Override
	public void uploadObjectToClient(Serializable object)
			throws RemoteException {
		updateObservers(object);
	}

	/**
	 * Instantiates a new RMI client.
	 * 
	 * @param host
	 *            the host
	 */
	public RMIClient(String host) {
		this.serverIP = host;
	}

	/**
	 * Connects the Client to the Server and tells him which DeviceType he
	 * wants.
	 * 
	 * @param deviceType
	 *            the device type
	 * @return the rMI server interface
	 * @throws RemoteException
	 *             the remote exception
	 * @throws NotBoundException
	 *             the not bound exception
	 * @throws AccessException
	 *             the access exception
	 * @throws ServerNotActiveException
	 *             the server not active exception
	 */
	public RMIServerInterface connect(Serializable deviceType)
			throws RemoteException, NotBoundException,
			ServerNotActiveException {
		Registry registry = LocateRegistry.getRegistry(getServerIP());
		setRMIServerInterface((RMIServerInterface) registry.lookup("Gymtastic"));
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);
		getRMIServerInterface().addClient(stub, deviceType);
		return getRMIServerInterface();

	}

	/**
	 * Disconnects the Client from the Server.
	 * 
	 * @throws RemoteException
	 *             the remote exception
	 */
	public void disconnect() throws RemoteException {
		getRMIServerInterface().removeClient(this);
	}

	/**
	 * Sets the server ip.
	 * 
	 * @param serverIP
	 *            the new server ip
	 */
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * Gets the server ip.
	 * 
	 * @return the server ip
	 */
	public String getServerIP() {
		return serverIP;
	}

	/**
	 * Gets the rmi server interface.
	 * 
	 * @return the rmi server interface
	 */
	public RMIServerInterface getRMIServerInterface() {
		return rmiServerInterface;
	}

	/**
	 * Send object to server over the RMI Interface
	 * 
	 * @param object
	 *            the object
	 * @throws TransmissionException
	 *             if transmission fails
	 */
	public void sendObjectToServer(Serializable object)
			throws TransmissionException {
		try {
			rmiServerInterface.uploadObjectToServer(object);
		} catch (RemoteException e) {
			throw new TransmissionException();
		}
	}

	/**
	 * Update the observers
	 * 
	 * @param object
	 *            the object
	 */
	private void updateObservers(Serializable object) {
		setChanged();
		notifyObservers(object);
	}

	public void setRMIServerInterface(RMIServerInterface rmiServerInterface) {
		this.rmiServerInterface = rmiServerInterface;
	}

}
