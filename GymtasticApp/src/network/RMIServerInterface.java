package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Dummy;

public interface RMIServerInterface extends Remote {
    public void addClient(RMIClientInterface client, int gymDevice) throws RemoteException;

    public void removeClient(RMIClientInterface client) throws RemoteException;
    
    public void uploadSquadToServer(Dummy dummy) throws RemoteException;

}
