package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.net.ConnectException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

/**
 * The Class RMIClient.
 */
public class RMIClient extends Observable implements RMIClientInterface {

	private String serverIP = "localhost";
	private RMIServerInterface rmiServerInterface;

	/**
	 * Instantiates a new RMI client.
	 *
	 * @throws Exception the exception
	 */
	public RMIClient() throws Exception {
	}

	/* (non-Javadoc)
	 * @see ch.hsr.gymtastic.technicalServices.network.RMIClientInterface#uploadObjectToClient(java.io.Serializable)
	 */
	@Override
	public void uploadObjectToClient(Serializable object)
			throws RemoteException {
		updateObservers(object);
	}

	/**
	 * Instantiates a new RMI client.
	 *
	 * @param host the host
	 */
	public RMIClient(String host) {
		this.serverIP = host;
	}

	/**
	 * Connects the Client to the Server and tells him which DeviceType he wants.
	 *
	 * @param deviceType the device type
	 * @return the rMI server interface
	 * @throws RemoteException the remote exception
	 * @throws NotBoundException the not bound exception
	 * @throws AccessException the access exception
	 * @throws ServerNotActiveException the server not active exception
	 */
	public RMIServerInterface connect(Serializable deviceType)
			throws RemoteException, NotBoundException, AccessException,
			ServerNotActiveException {
		Registry registry = LocateRegistry.getRegistry(getServerIP());
		rmiServerInterface = (RMIServerInterface) registry.lookup("Gymtastic");
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);
		rmiServerInterface.addClient(stub, deviceType);
		return rmiServerInterface;

	}

	/**
	 * Disconnects the Client from the Server.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void disconnect() throws RemoteException {
		rmiServerInterface.removeClient(this);
	}

	/**
	 * Sets the server ip.
	 *
	 * @param serverIP the new server ip
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
	public RMIServerInterface getRmiServerInterface() {
		return rmiServerInterface;
	}

	/**
	 * Send object to server over the RMI Interface
	 *
	 * @param object the object
	 * @throws ConnectException the connect exception
	 */
	public void sendObjectToServer(Serializable object) throws ConnectException {
		try {
			rmiServerInterface.uploadObjectToServer(object);
		} catch (RemoteException e) {
			throw new ConnectException();
		}
	}

	/**
	 * Update the observers
	 *
	 * @param object the object
	 */
	private void updateObservers(Serializable object) {
		setChanged();
		notifyObservers(object);
	}

}