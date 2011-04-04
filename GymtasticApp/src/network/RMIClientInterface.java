package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Squad;


public interface RMIClientInterface extends Remote {
	public void uploadSquadToClient(Squad squad) throws RemoteException;
}
