package ch.hsr.gymtastic.technicalServices.network;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;



public interface RMIServerInterface extends Remote {
    public void addClient(RMIClientInterface client, DeviceType deviceType) throws RemoteException, ServerNotActiveException;

    public void removeClient(RMIClientInterface client) throws RemoteException;

    public void uploadSquadToServer(Squad squad) throws RemoteException;
}