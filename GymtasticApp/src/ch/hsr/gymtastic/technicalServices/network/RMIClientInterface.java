package ch.hsr.gymtastic.technicalServices.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ch.hsr.gymtastic.domain.Squad;



public interface RMIClientInterface extends Remote {
	public void uploadSquadToClient(Squad squad) throws RemoteException;
}
