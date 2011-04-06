package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Vector;

import domain.Athlet;
import domain.ClientAllocation;
import domain.DeviceType;
import domain.Squad;

public class RMIServer extends Observable implements RMIServerInterface {

    Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();
    // ClientAllocation clientsWaitingForAllocation = new ClientAllocation();
    ClientAllocation clientsAllocated = new ClientAllocation();

    public RMIServer() throws RemoteException {
	RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(this, 0);
	Registry registry = LocateRegistry.createRegistry(1099);
	registry.rebind("Gymtastic", stub);
    }

    @Override
    public void addClient(RMIClientInterface client, DeviceType deviceType) throws RemoteException,
	    ServerNotActiveException {
	clientsWaitingForAllocation.add(new ClientInformation(client, RemoteServer.getClientHost(), deviceType));
	System.out.println("client added");
	System.out.println(deviceType);
	updateObservers();

    }

    @Override
    public void removeClient(RMIClientInterface client) throws RemoteException {
	
	updateObservers();
    }

    @Override
    public void uploadSquadToServer(Squad squad) throws RemoteException {
    //	updateObservers();
	for (Athlet athlete : squad.getAthlets()) {
	    System.out.println(athlete.getFirstName() + " " + athlete.getLastName());
	}
    }

    public Vector<ClientInformation> getClientsWaitingForAllocation() {
	return clientsWaitingForAllocation;
    }

    private void updateObservers() {
	setChanged();
	notifyObservers();
    }
}
