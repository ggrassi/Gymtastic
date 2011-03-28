package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Dummy;

public interface RMIClientInterface extends Remote {
	public void updateClient(Dummy dummy) throws RemoteException;
}
