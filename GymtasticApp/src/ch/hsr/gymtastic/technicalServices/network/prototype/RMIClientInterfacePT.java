package ch.hsr.gymtastic.technicalServices.network.prototype;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ch.hsr.gymtastic.domain.Dummy;


public interface RMIClientInterfacePT extends Remote {
	public void uploadSquadToClient(Dummy dummy) throws RemoteException;
}
