package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import domain.Dummy;

public class RMIServer implements RMIServerInterface {

	/**
	 * @param args
	 */
	public static final int FLOOR_EXCERICE = 0;
	public static final int POMMEL_HORSE = 1;
	public static final int STILL_RINGS = 2;
	public static final int VAULT = 3;
	public static final int PARALLEL_BARS = 4;
	public static final int HIGH_BAR = 5;

	ArrayList<RMIClientInterface> clients = new ArrayList<RMIClientInterface>(6);

	public RMIServer() {
		super();
	}

	@Override
	public void addClient(RMIClientInterface client, int gymDevice)
			throws RemoteException {
		if (clients.get(gymDevice) == null) {
			clients.add(gymDevice, client);
			System.out.println("Client added");
			clients.remove(client);
		} else {
			System.out.println("Ger�t Nr." + gymDevice + "ist bereits belegt.");
		}
	}

	@Override
	public void removeClient(RMIClientInterface client) throws RemoteException {
		clients.remove(client);
		System.out.println("Client removed!");

	}

	private void updateClients() throws RemoteException {
		for (RMIClientInterface client : clients) {
			Dummy tempDummy = new Dummy("tempDummy");
			System.out.println("Dummy mit name: " + tempDummy.getName()
					+ " wird an Client �bertragen");
			client.uploadSquadToClient(tempDummy);
		}
	}

	public static void main(String[] args) throws RemoteException {
		System.out.println("[GymTastic-Server]");
		RMIServer server = new RMIServer();
		System.out.println("[GymTastic] generate proxy");
		RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject
				.exportObject(server, 0);
		System.out.println("[GymTastic] registry()");
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("Server", stub);
		while (true) {
			server.updateClients();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void uploadSquadToServer(Dummy dummy) throws RemoteException {
		System.out.println("Bearbeiteter Dummy mit Name: " + dummy.getName()
				+ " wurde empfangen.");

	}

}
