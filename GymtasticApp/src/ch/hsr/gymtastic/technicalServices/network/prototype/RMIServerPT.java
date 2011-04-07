package ch.hsr.gymtastic.technicalServices.network.prototype;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import ch.hsr.gymtastic.domain.Dummy;


public class RMIServerPT extends Observable implements RMIServerInterfacePT {

    /**
     * @param args
     */

    Vector<RMIClientInterfacePT> clients = new Vector<RMIClientInterfacePT>();
    ArrayList<Dummy> dummies = new ArrayList<Dummy>(10);

    public static void main(String[] args) throws RemoteException {
	RMIServerPT server = new RMIServerPT();
	ch.hsr.gymtastic.presentation.prototype.ServerPrototype.newServerFrame(server);
    }

    public RMIServerPT() throws RemoteException {
	super();
	RMIServerInterfacePT stub = (RMIServerInterfacePT) UnicastRemoteObject.exportObject(this, 0);
	Registry registry = LocateRegistry.createRegistry(1099);
	registry.rebind("Gymtastic", stub);

	generateDummies();

    }

    @Override
    public synchronized void addClient(RMIClientInterfacePT client) throws RemoteException {
	clients.add(client);
	updateObservers();
    }

    @Override
    public void removeClient(RMIClientInterfacePT client) throws RemoteException {
	clients.remove(client);
	System.out.println("Client removed!");

	updateObservers();

    }

    public Vector<RMIClientInterfacePT> getClient() {
	return clients;
    }

    public ArrayList<Dummy> getDummies() {
	return dummies;
    }

    @Override
    public void uploadSquadToServer(Dummy dummy) throws RemoteException {
	dummies.add(dummy);
	updateObservers();

    }

    private void generateDummies() {
	for (int i = 0; i < 10; i++) {
	    dummies.add(new Dummy("Dummy" + i));
	}
    }

    private void updateObservers() {
	setChanged();
	notifyObservers();
    }

}
