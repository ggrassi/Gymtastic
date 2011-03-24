package network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Dummy {

    /**
     * @param args
     */
    private final static int serverPort = 7777;
    private static String LOOKUP_NAME = "Dummy";
    
    protected Server() throws RemoteException, MalformedURLException {
	super();
	LocateRegistry.createRegistry(serverPort );
	Naming.rebind("rmi://localhost", this);
    }
    
    @Override
    public String getDummyName(DummyClass dC) throws RemoteException {
	return dC.getName();
    }


    public static void main(String[] args) throws IOException {

	Server server = new Server();
	Dummy stub = (Dummy) UnicastRemoteObject.exportObject(server, 0);
	Registry registry = LocateRegistry.getRegistry();
	registry.rebind(LOOKUP_NAME, stub);
    }



}
