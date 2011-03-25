package network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Dummy {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	private final static int serverPort = 1099;
	private static String LOOKUP_NAME = "Dummy";
	private static String HOST = "localhost";
	private static String SERVICE_NAME = "RMI-Server";

	public Server() throws RemoteException, MalformedURLException {
		super();
		// Naming.rebind("//"+HOST+"/"+SERVICE_NAME, this);
	}

	public String getDummyName(DummyClass dC) throws RemoteException {
		return dC.getName();
	}

	public static void main(String[] args) throws IOException {

		Server server = new Server();
		LocateRegistry.createRegistry(serverPort);
		Dummy stub = (Dummy) UnicastRemoteObject.exportObject(server, 0);
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(LOOKUP_NAME, stub);
	}
}
