package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import network.RMIServerInterface;

import domain.Dummy;

public class RMIClient implements RMIClientInterface {

//	private static final String HOST = "152.96.233.102";
	private static final String HOST = "localhost";
//	private static final String HOST = "192.168.0.100";
	private RMIServerInterface rmiServerInterface;
	private Dummy dummy;

	public RMIClient() throws Exception {

		System.out.println("generate registry");
		Registry registry = LocateRegistry.getRegistry(HOST);

		System.out.println("registry lookup");
		rmiServerInterface = (RMIServerInterface) registry.lookup("Server");

		System.out.println("stub object creation");
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);

		System.out.println("Aufruf von addClient auf ServerStub");
		rmiServerInterface.addClient(stub, RMIServer.FLOOR_EXCERICE);
		System.out.println("Aufruf von addClient auf ServerStub ausgeführt");
		
		
		

	}

	private void serverUpdate() throws RemoteException {
		System.out.println("Bearbeiteter Dummy wird an Server übertragen.");
		rmiServerInterface.uploadSquadToServer(this.dummy);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Client gestartet");
		@SuppressWarnings("unused")
		RMIClient client = new RMIClient();

	}

	public void uploadSquadToClient(Dummy dummy) throws RemoteException {
		this.dummy = dummy;
		System.out.println("Dummy mit Name: " + dummy.getName()
				+ "ist bei Client eingetroffen.");
		
		this.dummy.setName("SuuuperDuuuuperDummy");
		System.out.println("Neuer Dummy name: " + this.dummy.getName());
		
		serverUpdate();

	}

}
