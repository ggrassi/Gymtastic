package network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

import views.ClientPrototype;
import domain.Dummy;

public class RMIClient extends Observable implements RMIClientInterface {

	private String serverIP = "localhost";
	private RMIServerInterface rmiServerInterface;

	private Dummy dummy;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		RMIClient client = new RMIClient();
		ClientPrototype.newClientPrototypeFrame(client);
	}

	public RMIClient() throws Exception {

	}

	public void connect() throws RemoteException, NotBoundException,
			AccessException {
		Registry registry = LocateRegistry.getRegistry(getServerIP());
		rmiServerInterface = (RMIServerInterface) registry.lookup("Server");
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);
		rmiServerInterface.addClient(stub, RMIServer.FLOOR_EXCERICE);
	}

	public void disconnect() throws RemoteException {
		rmiServerInterface.removeClient(this);

	}

	public void updateServer() throws RemoteException {
		rmiServerInterface.uploadSquadToServer(dummy);
	}

	public void uploadSquadToClient(Dummy dummy) throws RemoteException {
		this.dummy = dummy;
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public Dummy getDummy() {
		return dummy;
	}

	public RMIServerInterface getRmiServerInterface() {
		return rmiServerInterface;
	}

}
