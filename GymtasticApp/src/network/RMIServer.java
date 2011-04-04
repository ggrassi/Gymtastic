package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
<<<<<<< HEAD
import java.util.Vector;

=======
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

import network.prototype.RMIClientInterfacePT;
import network.prototype.RMIServerInterfacePT;

>>>>>>> 68a2cbe122ac2d8e7a0560f2db259b485fae2be6
import domain.ClientAllocation;
import domain.DeviceType;
import domain.Squad;

public class RMIServer implements RMIServerInterface {

    public RMIServer() throws RemoteException {
	RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(this, 0);
	Registry registry = LocateRegistry.createRegistry(1099);
	registry.rebind("Gymtastic", stub);
    }

    Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();
    ClientAllocation clientsAllocated = new ClientAllocation();
    
    @Override
    public void addClient(RMIClientInterface client, DeviceType deviceType) throws RemoteException, ServerNotActiveException {
	clientsWaitingForAllocation.add(new ClientInformation(client, RemoteServer.getClientHost()));
	System.out.println("client added");
	System.out.println(deviceType);

    }

    @Override
    public void removeClient(RMIClientInterface client) throws RemoteException {

    }

    @Override
    public void uploadSquadToServer(Squad squad) throws RemoteException {

    }

    public Vector<ClientInformation> getClientsWaitingForAllocation() {
	return clientsWaitingForAllocation;
    }
    
    

   

  

}
