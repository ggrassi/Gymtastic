package ch.hsr.gymtastic.technicalServices.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Vector;

import ch.hsr.gymtastic.domain.Athlet;
import ch.hsr.gymtastic.domain.ClientAllocation;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class RMIServer extends Observable implements RMIServerInterface {

    Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();
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
    public void uploadSquadToServer(Squad temp) throws RemoteException {

	DBConnection db = new DBConnection();
	for (Athlet athlet : temp.getAthlets()) {
	    Athlet foundAthlete = db.getEm().find(Athlet.class, athlet.getAthletId());
	    foundAthlete.setFirstName(athlet.getFirstName());
	    System.out.println("Vorname: " + athlet.getFirstName() + " Nachname: " + athlet.getLastName());
	}

	db.commit();
	db.closeConnection();
	
    }

    public Vector<ClientInformation> getClientsWaitingForAllocation() {
	return clientsWaitingForAllocation;
    }

    private void updateObservers() {
	setChanged();
	notifyObservers();
    }
}
