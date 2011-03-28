package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    public void addClient(RMIClientInterface client) throws RemoteException;

    public void removeClient() throws RemoteException;

}
