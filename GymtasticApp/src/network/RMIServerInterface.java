package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Dummy;

public interface RMIServerInterface extends Remote {
    public void addClient(RMIClientInterface client) throws RemoteException;

    public void removeClient(RMIClientInterface client) throws RemoteException;
    
    public void updateServer(Dummy dummy) throws RemoteException;

}
