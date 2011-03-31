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

	// private static final String HOST = "152.96.233.102";
	// private static final String HOST = "192.168.0.100";
	private String serverIP = "localhost";
	private RMIServerInterface rmiServerInterface;

	private Dummy dummy;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Client gestartet");
		RMIClient client = new RMIClient();
		ClientPrototype.newClientPrototypeFrame(client);

	}

	public RMIClient() throws Exception {

	}

	public void connect() throws RemoteException, NotBoundException,
			AccessException {
		System.out.println("generate registry");
		Registry registry = LocateRegistry.getRegistry(getServerIP());

		System.out.println("registry lookup");
		rmiServerInterface = (RMIServerInterface) registry.lookup("Server");

		System.out.println("stub object creation");
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);
		System.out.println("Aufruf von addClient auf ServerStub");
		rmiServerInterface.addClient(stub, RMIServer.FLOOR_EXCERICE);
		System.out.println("Aufruf von addClient auf ServerStub ausgeführt");
	}

	public void disconnect() throws RemoteException {
		rmiServerInterface.removeClient(this);

	}

	private void serverUpdate() throws RemoteException {
		System.out.println("Bearbeiteter Dummy wird an Server übertragen.");
		rmiServerInterface.uploadSquadToServer(dummy);
	}

	public void uploadSquadToClient(Dummy dummy) throws RemoteException {
		System.out.println("Received new Dummy " + dummy);
		this.dummy = dummy;
		updateObservers();

		/*
		 * System.out.println("Dummy mit Name: " + dummy.getName() +
		 * "ist bei Client eingetroffen.");
		 * 
		 * this.dummy.setName("SuuuperDuuuuperDummy");
		 * System.out.println("Neuer Dummy name: " + this.dummy.getName());
		 * 
		 * serverUpdate();
		 */

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
