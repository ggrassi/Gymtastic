package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class RMIServer implements RMIServerInterface {

    /**
     * @param args
     */
    Vector<RMIClientInterface> clients = new Vector<RMIClientInterface>();

    public RMIServer() {
	super();
    }

    @Override
    public void addClient(RMIClientInterface client) throws RemoteException {
	clients.add(client);
	System.out.println("Client added");

    }

    @Override
    public void removeClient(RMIClientInterface client) throws RemoteException {
	clients.remove(client);
	System.out.println("Client removed!");

    }
    
    private void updateClients() throws RemoteException{
	for(RMIClientInterface client: clients){
	    client.updateClient();
	}
    }

    public static void main(String[] args) throws RemoteException {
	System.out.println("[GymTastic-Server]");
	RMIServer server = new RMIServer();
	System.out.println("[GymTastic] generate proxy");
	RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(server, 0);
	System.out.println("[GymTastic] registry()");
	Registry registry = LocateRegistry.createRegistry(1099);
	registry.rebind("Server", stub);
	while(true){
	    server.updateClients();
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

    }

}
