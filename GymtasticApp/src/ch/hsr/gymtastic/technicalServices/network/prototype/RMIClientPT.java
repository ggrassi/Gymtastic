package ch.hsr.gymtastic.technicalServices.network.prototype;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

import ch.hsr.gymtastic.domain.Dummy;
import ch.hsr.gymtastic.presentation.prototype.ClientPrototype;


public class RMIClientPT extends Observable implements RMIClientInterfacePT {

	private String serverIP = "localhost";
	private RMIServerInterfacePT rmiServerInterface;

	private Dummy dummy;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		RMIClientPT client = new RMIClientPT();
		ClientPrototype.newClientPrototypeFrame(client);
	}

	public RMIClientPT() throws Exception {
	}

	public RMIClientPT(String host) throws Exception {
		this.serverIP = host;
	}

	public void connect() throws RemoteException, NotBoundException,
			AccessException {
		Registry registry = LocateRegistry.getRegistry(getServerIP());
		rmiServerInterface = (RMIServerInterfacePT) registry.lookup("Gymtastic");
		RMIClientInterfacePT stub = (RMIClientInterfacePT) UnicastRemoteObject
				.exportObject(this, 0);
		rmiServerInterface.addClient(stub);
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

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public Dummy getDummy() {
		return dummy;
	}

	public RMIServerInterfacePT getRmiServerInterface() {
		return rmiServerInterface;
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

}
