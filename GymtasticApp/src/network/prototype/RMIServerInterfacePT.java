package network.prototype;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Dummy;

public interface RMIServerInterfacePT extends Remote {
    public void addClient(RMIClientInterfacePT client) throws RemoteException;

    public void removeClient(RMIClientInterfacePT client) throws RemoteException;

    public void uploadSquadToServer(Dummy dummy) throws RemoteException;

}
