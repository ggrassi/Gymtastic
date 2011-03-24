package network;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    /**
     * @param args
     */
//    private final static int serverPort = 7777;
    private final static String serverName = "localhost";
    private static String LOOKUP_NAME = "Dummy";

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {

	Registry registry = LocateRegistry.getRegistry(serverName);
	Dummy stub = (Dummy) registry.lookup(LOOKUP_NAME);
	System.out.println(stub.getDummyName(new DummyClass("F�ssi")));

    }

}
