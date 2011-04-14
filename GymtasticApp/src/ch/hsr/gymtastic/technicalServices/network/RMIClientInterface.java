package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface RMIClientInterface extends Remote {
	public void uploadSquadToClient(Serializable object) throws RemoteException;
}
