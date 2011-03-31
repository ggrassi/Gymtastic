package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import domain.Dummy;

public class RMIServer extends Observable implements RMIServerInterface {

    /**
     * @param args
     */

    Vector<RMIClientInterface> clients = new Vector<RMIClientInterface>();
    ArrayList<Dummy> dummies = new ArrayList<Dummy>(10);

    public static void main(String[] args) throws RemoteException {
	RMIServer server = new RMIServer();
	views.ServerPrototype.newServerFrame(server);
    }

    public RMIServer() throws RemoteException {
	super();
	RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(this, 0);
	Registry registry = LocateRegistry.createRegistry(1099);
	registry.rebind("Gymtastic", stub);

	generateDummies();

    }

    @Override
    public synchronized void addClient(RMIClientInterface client) throws RemoteException {
	clients.add(client);
	updateObservers();
    }

    @Override
    public void removeClient(RMIClientInterface client) throws RemoteException {
	clients.remove(client);
	System.out.println("Client removed!");

	updateObservers();

    }

    public Vector<RMIClientInterface> getClient() {
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
