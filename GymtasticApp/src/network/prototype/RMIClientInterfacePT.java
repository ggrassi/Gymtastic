package network.prototype;

import java.rmi.Remote;
import java.rmi.RemoteException;

import domain.Dummy;

public interface RMIClientInterfacePT extends Remote {
	public void uploadSquadToClient(Dummy dummy) throws RemoteException;
}
