package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Vector;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class RMIServer extends Observable implements RMIServerInterface {

	Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();

	public RMIServer() throws RemoteException {
		RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject
				.exportObject(this, 0);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("Gymtastic", stub);
	}

	@Override
	public void addClient(RMIClientInterface client, Serializable deviceType)
			throws RemoteException, ServerNotActiveException {
		clientsWaitingForAllocation.add(new ClientInformation(client,
				RemoteServer.getClientHost(), (DeviceType) deviceType));
		System.out.println("Client added");
		client.uploadCupInformation("SuperDuperCup", "Rapperswil", "Fasser AG", "01.01.2011", "02.02.2011");

		updateObservers();

	}

	@Override
	public void removeClient(RMIClientInterface client) throws RemoteException {

		/*
		 * TODO
		 */
		
		updateObservers();
	}

	@Override
	public void uploadSquadToServer(Serializable object) throws RemoteException {
		Squad temp = (Squad) object;
		DBConnection db = new DBConnection();
		for (Athlete athlet : temp.getAthlets()) {
			Athlete foundAthlete = db.getEm().find(Athlete.class,
					athlet.getAthleteId());
			foundAthlete.setFirstName(athlet.getFirstName());
			System.out.println("Vorname: " + athlet.getFirstName()
					+ " Nachname: " + athlet.getLastName());
		}

		db.commit();
		db.closeConnection();

	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return clientsWaitingForAllocation;
	}
}
