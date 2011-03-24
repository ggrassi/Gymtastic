package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Dummy {

    /**
     * @param args
     */
    // private final static int serverPort = 7777;
    private static String LOOKUP_NAME = "Dummy";

    public static void main(String[] args) throws IOException {

	Server server = new Server();
	Dummy stub = (Dummy) UnicastRemoteObject.exportObject(server, 0);
	Registry registry = LocateRegistry.getRegistry();
	registry.rebind(LOOKUP_NAME, stub);
    }

    @Override
    public String getDummyName(DummyClass dC) throws RemoteException {
	return dC.getName();
    }

}
