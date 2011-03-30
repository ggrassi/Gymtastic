package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import network.RMIServerInterface;

import domain.Dummy;

public class RMIClient implements RMIClientInterface {

	private static final String HOST = "localhost";
	private RMIServerInterface rmiServerInterface;
	private Dummy dummy;

	public RMIClient() throws Exception {

		Registry registry = LocateRegistry.getRegistry(HOST);

		rmiServerInterface = (RMIServerInterface) registry.lookup("Server");

		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);

		System.out.println("Aufruf von addClient auf ServerStub");
		rmiServerInterface.addClient(stub);
		System.out.println("Aufruf von addClient auf ServerStub ausgeführt");
		
		
		

	}

	private void serverUpdate() throws RemoteException {
		System.out.println("Bearbeiteter Dummy wird an Server übertragen.");
		rmiServerInterface.updateServer(this.dummy);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Client gestartet");
		@SuppressWarnings("unused")
		RMIClient client = new RMIClient();

	}

	public void updateClient(Dummy dummy) throws RemoteException {
		this.dummy = dummy;
		System.out.println("Dummy mit Name: " + dummy.getName()
				+ "ist bei Client eingetroffen.");
		
		this.dummy.setName("SuuuperDuuuuperDummy");
		System.out.println("Neuer Dummy name: " + this.dummy.getName());
		
		serverUpdate();

	}

}
