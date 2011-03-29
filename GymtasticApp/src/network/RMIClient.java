package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import domain.Dummy;

public class RMIClient implements RMIClientInterface {

    private static final String HOST = "152.96.232.133";
    private RMIServerInterface rmiServerInterface;

    public RMIClient() throws Exception {

	Registry registry = LocateRegistry.getRegistry(HOST);

	rmiServerInterface = (RMIServerInterface) registry.lookup("Server");

	RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject.exportObject(this, 0);

	System.out.println("davor");
	rmiServerInterface.addClient(stub);
	System.out.println("davor");

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
//	System.out.println("Client Update");
    	dummy.setName("fjkldsajflk�as");

    }

}
