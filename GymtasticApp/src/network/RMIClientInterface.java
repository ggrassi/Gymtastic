package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {
	public void updateClient(Object o) throws RemoteException;
}
