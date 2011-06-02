package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The Interface RMIClientInterface
 */
public interface RMIClientInterface extends Remote {

	/**
	 * Upload object to client.
	 * 
	 * @param object
	 *            the object
	 * @throws RemoteException
	 *             the remote exception
	 */
	void uploadObjectToClient(Serializable object) throws RemoteException;
}
