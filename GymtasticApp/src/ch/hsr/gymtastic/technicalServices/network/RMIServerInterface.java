package ch.hsr.gymtastic.technicalServices.network;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;



/**
 * The Interface RMIServerInterface represents all Methods 
 * which can be selected from the RMIClient
 */
public interface RMIServerInterface extends Remote {
    
    /**
     * Adds a Client to the Server.
     *
     * @param client the client
     * @param deviceType the device type
     * @throws RemoteException the remote exception
     * @throws ServerNotActiveException the server not active exception
     */
    public void addClient(RMIClientInterface client, Serializable deviceType) throws RemoteException, ServerNotActiveException;

    /**
     * Removes a client from the Server
     *
     * @param client the client
     * @throws RemoteException the remote exception
     */
    public void removeClient(RMIClientInterface client) throws RemoteException;

    /**
     * Uploads an object to the server.
     *
     * @param object the object
     * @throws RemoteException the remote exception
     */
    public void uploadObjectToServer(Serializable object) throws RemoteException;
}